package cz.itnetwork.insurance.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pojisteni")
public class InsuranceController {

    @GetMapping
    public String renderInsurance(){
        return "pages/pojisteni/index";
    }
}
