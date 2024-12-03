package cz.itnetwork.insurance.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * This method renders the home page of the application.
     *
     * @return The path to the template ("pages/home/index") that displays the home page.
     */
    @GetMapping
    public String renderHome() {
        return "pages/home/index";
    }
}
