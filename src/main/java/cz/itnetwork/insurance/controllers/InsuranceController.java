package cz.itnetwork.insurance.controllers;


import cz.itnetwork.insurance.data.repositories.PersonRepository;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.PersonDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import cz.itnetwork.insurance.models.dto.mappers.PersonMapper;
import cz.itnetwork.insurance.models.exceptions.InsuranceNotFoundException;
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
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;


    @Autowired
    InsuranceMapper insuranceMapper;


    @GetMapping
    public String renderInsurance(Model model, PersonDTO personDTO){
        List<InsuranceDTO> insuranceList =  insuranceService.insuranceList();
        model.addAttribute("insuranceList", insuranceList);
        return "pages/insurance/index";
    }

    @GetMapping("/new-insurance/{personId}")
    public String renderCreateForm(@PathVariable long personId, @ModelAttribute InsuranceDTO insuranceDTO){

        return "pages/insurance/create";
    }

    @PostMapping("/new-insurance/{personId}")
    public String create(@Valid @ModelAttribute InsuranceDTO insuranceDTO, BindingResult result, @PathVariable long personId, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return renderCreateForm(personId,insuranceDTO);
        }
        redirectAttributes.addFlashAttribute("success", "Pojištění přidáno");
        insuranceService.create(insuranceDTO);

        return "redirect:/insurance";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes){
        insuranceService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Pojištění smazáno");
        return "redirect:/insurance";
    }

    @GetMapping("/edit/{id}")
    public String renderEditForm (@PathVariable long id, InsuranceDTO insuranceDTO){
        InsuranceDTO fetchedDTO = insuranceService.getById(id);
        insuranceMapper.updateInsuranceDTO(fetchedDTO,insuranceDTO);
        return "pages/insurance/edit";
    }

    @PostMapping("/edit/{id}")
    public String editInsurance(@PathVariable long id, @Valid @ModelAttribute InsuranceDTO insuranceDTO, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return renderEditForm(id,insuranceDTO);
        }

        redirectAttributes.addFlashAttribute("success", "Pojištění změněno");
        insuranceService.saveUpdatedInsurance(insuranceDTO);
        return "redirect:/insurance";

    }

    @GetMapping("/detail/{id}")
    public String renderInsuranceDetail(@PathVariable long id, Model model){
        InsuranceDTO insuranceDTO = insuranceService.getById(id);
        model.addAttribute("insurance", insuranceDTO);
        return "pages/insurance/detail";
    }

    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "Pojištění nenalezeno");

        return "redirect:/insurance";
    }











}
