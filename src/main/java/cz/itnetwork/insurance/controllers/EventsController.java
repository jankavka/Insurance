package cz.itnetwork.insurance.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/udalosti")
public class EventsController {


    @GetMapping
    public String renderEvents(){
        return "pages/udalosti/index";
    }
}
