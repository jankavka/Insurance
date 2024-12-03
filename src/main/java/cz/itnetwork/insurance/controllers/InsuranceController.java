package cz.itnetwork.insurance.controllers;


import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import cz.itnetwork.insurance.models.exceptions.InsuranceNotFoundException;
import cz.itnetwork.insurance.models.services.InsuranceService;
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

    /**
     * This method renders all insurances for all persons in database
     *
     * @param model The model providing attributes for the template. This model includes a list of insurances
     *              that will be used to render the insurance page.
     * @return The template path ("pages/insurance/index") that will be used to render the page.
     */
    @GetMapping
    public String renderInsurance(Model model) {
        List<InsuranceDTO> listOfAllInsurances = insuranceService.showAllInsurances();
        model.addAttribute("insuranceList", listOfAllInsurances);
        return "pages/insurance/index";
    }

    /**
     * This method renders form for creating a new insurance for a specific person
     *
     * @param personId     The ID of the person for whom we are creating the new insurance policy,
     *                     which will be passed to the template using Thymeleaf.
     * @param insuranceDTO A data transfer object that provides the necessary information for the
     *                     insurance form, to be used in the template with Thymeleaf.
     * @return The string representation of the template path ("pages/insurance/create")
     * that will be rendered to display the form.
     */
    @GetMapping("/new-insurance/{personId}")
    public String renderCreateForm(@PathVariable long personId, @ModelAttribute InsuranceDTO insuranceDTO) {

        return "pages/insurance/create";
    }


    /**
     * This method creates a new insurance policy for a specific person.
     *
     * @param insuranceDTO       Data transfer object that carries the information of a new insurance from the template
     *                           using Thymeleaf.
     * @param result             An object for catching validation errors in insuranceDTO. If there are errors application
     *                           will return to renderCreateForm().
     * @param personId           The ID of the person for whom we are creating the new insurance policy
     *                           which is contained in the URL path and associates the insurance policy with the specific person.
     * @param redirectAttributes An object which provides a success information to a template in a case of
     *                           successful operation.
     * @return The redirect path to the template that displays all existing insurances for all persons
     * *         in the database.
     */
    @PostMapping("/new-insurance/{personId}")
    public String create(@Valid @ModelAttribute InsuranceDTO insuranceDTO, BindingResult result, @PathVariable long personId, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return renderCreateForm(personId, insuranceDTO);
        }
        redirectAttributes.addFlashAttribute("success", "Pojištění přidáno");
        insuranceService.create(insuranceDTO);

        return "redirect:/insurance";
    }


    /**
     * This method handles the deletion of an insurance policy identified by the provided ID.
     *
     * @param id                 The ID of the insurance policy to be deleted, which is passed via the URL path.
     * @param redirectAttributes An object that allows adding flash attributes, which are used to convey
     *                           success messages to the template after redirection.
     * @return A redirect path to the insurance listing page, indicating that the operation has been
     * successfully completed.
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        insuranceService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Pojištění smazáno");

        return "redirect:/insurance";
    }


    /**
     * This method renders a form for editing an existing insurance policy identified by the provided ID.
     *
     * @param id           The ID of the insurance policy to be edited, which is passed via the URL path.
     * @param insuranceDTO A data transfer object that will hold the updated values for the insurance policy.
     *                     This object is populated with information fetched from the database.
     * @return The path to the template ("pages/insurance/edit") that displays the edit form for the selected
     * insurance policy.
     */
    @GetMapping("/edit/{id}")
    public String renderEditForm(@PathVariable long id, InsuranceDTO insuranceDTO) {
        InsuranceDTO fetchedDTO = insuranceService.getById(id);
        insuranceMapper.updateInsuranceDTO(fetchedDTO, insuranceDTO);

        return "pages/insurance/edit";
    }


    /**
     * This method processes the submission of the edit form to update an existing insurance policy
     * identified by the provided ID.
     *
     * @param id                 The ID of the insurance policy to be updated, which is passed via the URL path.
     * @param insuranceDTO       A data transfer object containing the updated information for the insurance policy.
     *                           This object is validated for correctness before the update.
     * @param result             An object that contains any validation errors that may occur during the binding of
     *                           the insuranceDTO. If there are errors, the edit form will be re-rendered.
     * @param redirectAttributes An object that allows you to add flash attributes, which convey success messages
     *                           to the template after redirection.
     * @return A redirect path to the insurance listing page upon successful update or
     * the edit form if validation errors are present.
     */
    @PostMapping("/edit/{id}")
    public String editInsurance(@PathVariable long id, @Valid @ModelAttribute InsuranceDTO insuranceDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return renderEditForm(id, insuranceDTO);
        }

        redirectAttributes.addFlashAttribute("success", "Pojištění změněno");
        insuranceService.updateInsurance(insuranceDTO);

        return "redirect:/insurance";

    }


    /**
     * This method renders the details of a specific insurance policy identified by the provided ID.
     *
     * @param id    The ID of the insurance policy whose details are to be displayed, which is passed via the URL path.
     * @param model The model object that holds attributes for the view, allowing data to be passed to the template.
     *              In this case, it includes the insurance policy details retrieved from the service.
     * @return The path to the template ("pages/insurance/detail") that displays the detailed information
     * of the selected insurance policy.
     */
    @GetMapping("/detail/{id}")
    public String renderInsuranceDetail(@PathVariable long id, Model model) {
        InsuranceDTO insuranceDTO = insuranceService.getById(id);
        model.addAttribute("insurance", insuranceDTO);

        return "pages/insurance/detail";
    }


    /**
     * This method handles exceptions of type InsuranceNotFoundException, which are thrown
     * when an insurance policy is not found in the database.
     *
     * @param redirectAttributes An object that allows you to add flash attributes, which are used to
     *                           convey error messages to the template after redirection.
     * @return A redirect path to the insurance listing page, along with an error message indicating
     * that the requested insurance policy was not found.
     */
    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Pojištění nenalezeno");

        return "redirect:/insurance";
    }

}
