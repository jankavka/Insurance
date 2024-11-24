package cz.itnetwork.insurance.controllers;


import cz.itnetwork.insurance.data.entities.PersonEntity;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.PersonDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import cz.itnetwork.insurance.models.services.InsuranceService;
import cz.itnetwork.insurance.models.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pojisteni")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @Autowired
    PersonService personService;

    @Autowired
    InsuranceMapper insuranceMapper;

    @GetMapping
    public String renderInsurance(InsuranceDTO insuranceDTO, Model model){
        List<InsuranceDTO> insuranceList =  insuranceService.insuranceList();
        model.addAttribute("insuranceList", insuranceList);
        return "pages/pojisteni/index";
    }

    @GetMapping("/nove-pojisteni/{personId}")
    public String renderCreateForm(@PathVariable long personId, @ModelAttribute InsuranceDTO insuranceDTO){
        insuranceDTO.setPersonId(personId);
        return "pages/pojisteni/create";
    }

    @PostMapping("/nove-pojisteni/")
    public String create(@Valid @ModelAttribute InsuranceDTO insuranceDTO){
        insuranceService.create(insuranceDTO);

        return "redirect:/pojisteni";
    }

    @GetMapping("/smazat/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes){
        insuranceService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Pojištění smazáno");
        return "redirect:/pojisteni";
    }









}
