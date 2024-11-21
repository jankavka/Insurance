package cz.itnetwork.insurance.controllers;


import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pojisteni")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @GetMapping
    public String renderInsurance(InsuranceDTO insuranceDTO){
        return "pages/pojisteni/index";
    }

    @GetMapping("/nove-pojisteni")
    public String renderCreateForm(InsuranceDTO insuranceDTO){
        return "pages/pojisteni/create";
    }

    @PostMapping("/nove-pojisteni")
    public String create(@Valid @ModelAttribute InsuranceDTO insuranceDTO){
        insuranceService.create(insuranceDTO);
        return "redirect:/pojisteni";
    }

}
