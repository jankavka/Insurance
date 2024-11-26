package cz.itnetwork.insurance.controllers;


import cz.itnetwork.insurance.data.entities.PersonEntity;
import cz.itnetwork.insurance.data.repositories.PersonRepository;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.PersonDTO;
import cz.itnetwork.insurance.models.dto.mappers.PersonMapper;
import cz.itnetwork.insurance.models.exceptions.PersonNotFoundException;
import cz.itnetwork.insurance.models.services.InsuranceService;
import cz.itnetwork.insurance.models.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pojistenci")
public class PersonsController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    InsuranceService insuranceService;

    @GetMapping
    public String renderInsuredPersons(Model model){
        List<PersonDTO> persons = personService.showAll();
        model.addAttribute("persons", persons);

        return "pages/pojistenci/index";
    }

    @GetMapping("/novy")
    public String renderCreatePersonForm(@ModelAttribute PersonDTO personDTO){
        return "pages/pojistenci/create";
    }

    @PostMapping
    public String createPerson(@Valid @ModelAttribute PersonDTO personDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return renderCreatePersonForm(personDTO);
        }
        personService.create(personDTO);
        redirectAttributes.addFlashAttribute("success","Pojištěnec vytvořen");

        return "redirect:/pojistenci";
    }

    @GetMapping("/detail/{personId}")
    public String renderPersonDetail(Model model, @PathVariable long personId){
        List<InsuranceDTO> insurances = insuranceService.insuranceListById(personId);
        PersonDTO person = personService.findById(personId);
        model.addAttribute("person", person);
        model.addAttribute("insurances", insurances);


        return "pages/pojistenci/detail";
    }

    @GetMapping("/edit/{personId}")
    public String renderEditForm(@PathVariable long personId, PersonDTO personDTO) {
        PersonDTO fetchedDTO = personService.findById(personId);
        personMapper.updatePersonDTO(fetchedDTO,personDTO);

        return "pages/pojistenci/edit";
    }

    @PostMapping("/edit/{personId}")
    public String editPerson(@PathVariable long personId,@Valid PersonDTO personDTO, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return renderEditForm(personId,personDTO);

        }
        personService.saveUpdatedEntity(personDTO);
        redirectAttributes.addFlashAttribute("success","Záznam pojištěnce změněn");

        return "redirect:/pojistenci";
    }

    @GetMapping("/delete/{personId}")
    public String deletePerson(@PathVariable long personId, RedirectAttributes redirectAttributes){
        personService.delete(personId);
        redirectAttributes.addFlashAttribute("success","Pojištěnec smazán");

        return "redirect:/pojistenci";
    }

    @ExceptionHandler({PersonNotFoundException.class})
    public String handlePersonNotFoundException(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen");

        return "redirect:/pojistenci";
    }

}
