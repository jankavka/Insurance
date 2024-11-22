package cz.itnetwork.insurance.controllers;


import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import cz.itnetwork.insurance.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pojisteni")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @Autowired
    InsuranceMapper insuranceMapper;

    @GetMapping
    public String renderInsurance(InsuranceDTO insuranceDTO, Model model){
        List<InsuranceDTO> insuranceList =  insuranceService.insuranceList();
        model.addAttribute("insuranceList", insuranceList);
        return "pages/pojisteni/index";
    }

    @GetMapping("/nove-pojisteni")
    public String renderCreateForm(InsuranceDTO insuranceDTO){
        return "pages/pojisteni/create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute InsuranceDTO insuranceDTO){
        insuranceService.create(insuranceDTO);
        return "redirect:/pojisteni";
    }

    @GetMapping("/upravit/{id}")
    public String renderEditForm(@ModelAttribute InsuranceDTO insuranceDTO, @PathVariable long id){
        InsuranceDTO dtoFromDatabase = insuranceService.getById(id);
        insuranceMapper.updateInsuranceDTO(dtoFromDatabase, insuranceDTO);

        return "pages/pojisteni/edit";
    }

    @PostMapping("/upravit/{id}")
    public String edit(@Valid @ModelAttribute InsuranceDTO insuranceDTO, @PathVariable long id){
        insuranceService.edit(insuranceDTO);
        return "redirect:/pojisteni";
    }

    @GetMapping("/smazat/{id}")
    public String delete(@PathVariable long id){
        insuranceService.deleteInsurance(id);
        return "redirect:/pojisteni";
    }



}
