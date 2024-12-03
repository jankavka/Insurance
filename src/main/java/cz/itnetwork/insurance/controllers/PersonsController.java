package cz.itnetwork.insurance.controllers;


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
@RequestMapping("/person")
public class PersonsController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    InsuranceService insuranceService;


    /**
     * This method renders a list of all insured persons available in the system.
     *
     * @param model The model object that holds attributes for the view, allowing data to be passed to the template.
     *              In this case, it includes the list of persons retrieved from the service.
     * @return The path to the template ("pages/person/index") that displays the information about all insured persons.
     */
    @GetMapping
    public String renderInsuredPersons(Model model) {
        List<PersonDTO> persons = personService.showAllPersons();
        model.addAttribute("persons", persons);

        return "pages/person/index";
    }


    /**
     * This method renders a form for creating a new insured person.
     *
     * @param personDTO A data transfer object that holds the information of the person to be created.
     *                  It is used to bind form data when submitted.
     * @return The path to the template ("pages/person/create") that displays the form for entering
     * details of a new insured person.
     */
    @GetMapping("/new")
    public String renderCreatePersonForm(@ModelAttribute PersonDTO personDTO) {
        return "pages/person/create";
    }


    /**
     * This method processes the submission of the form to create a new insured person.
     *
     * @param personDTO          A data transfer object containing the information for the new insured person.
     *                           This object is validated for correctness before creation.
     * @param bindingResult      An object that holds validation errors, if any, that occur during the binding
     *                           of the personDTO. If errors are present, the create person form will be re-rendered.
     * @param redirectAttributes An object that allows adding flash attributes, which are used to convey
     *                           success messages to the template after redirection.
     * @return A redirect path to the listing page of insured persons upon successful creation,
     * or the create person form if validation errors are present.
     */
    @PostMapping
    public String createPerson(@Valid @ModelAttribute PersonDTO personDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return renderCreatePersonForm(personDTO);
        }
        personService.create(personDTO);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec vytvořen");

        return "redirect:/person";
    }


    /**
     * This method renders the details of a specific insured person, along with their associated insurance policies.
     *
     * @param model    The model object that holds attributes for the view, allowing data to be passed to the template.
     *                 It includes details of the person and their insurance policies.
     * @param personId The ID of the insured person whose details are to be displayed, which is passed via the URL path.
     * @return The path to the template ("pages/person/detail") that displays the detailed information
     * about the selected insured person and their associated insurance policies.
     */
    @GetMapping("/detail/{personId}")
    public String renderPersonDetail(Model model, @PathVariable long personId) {
        List<InsuranceDTO> insurances = insuranceService.showInsuranceListByPersonId(personId);
        PersonDTO person = personService.findById(personId);
        model.addAttribute("person", person);
        model.addAttribute("insurances", insurances);

        return "pages/person/detail";
    }


    /**
     * This method renders a form for editing the details of a specific insured person identified by the provided ID.
     *
     * @param personId  The ID of the insured person whose details are to be edited, which is passed via the URL path.
     * @param personDTO A data transfer object that will be populated with the current values of the insured
     *                  person's details fetched from the service, providing a basis for the updated
     *                  information in the form.
     * @return The path to the template ("pages/person/edit") that displays the form for editing the selected
     * insured person's details.
     */
    @GetMapping("/edit/{personId}")
    public String renderEditForm(@PathVariable long personId, PersonDTO personDTO) {
        PersonDTO fetchedDTO = personService.findById(personId);
        personMapper.updatePersonDTO(fetchedDTO, personDTO);

        return "pages/person/edit";
    }


    /**
     * This method processes the submission of the form to update the details of an insured person identified
     * by the provided ID.
     *
     * @param personId           The ID of the insured person whose details are to be updated, passed via the URL path.
     * @param personDTO          A data transfer object containing the updated information for the insured person,
     *                           which is validated before the update.
     * @param result             An object that holds validation errors, if any, that occur during the binding
     *                           of the personDTO. If errors are present, the edit form will be re-rendered.
     * @param redirectAttributes An object that allows adding flash attributes, which are used to convey
     *                           success messages to the template after redirection.
     * @return A redirect path to the listing page of insured persons upon successful update,
     * or the edit form if validation errors are present.
     */
    @PostMapping("/edit/{personId}")
    public String editPerson(@PathVariable long personId, @Valid PersonDTO personDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return renderEditForm(personId, personDTO);
        }
        personService.updatePerson(personDTO);
        redirectAttributes.addFlashAttribute("success", "Záznam pojištěnce změněn");

        return "redirect:/person";
    }


    /**
     * This method handles the deletion of an insured person identified by the provided ID.
     *
     * @param personId           The ID of the insured person to be deleted, which is passed via the URL path.
     * @param redirectAttributes An object that allows adding flash attributes, which are used to convey
     *                           success messages to the template after redirection.
     * @return A redirect path to the listing page of insured persons following the successful deletion
     * of the specified insured person.
     */
    @GetMapping("/delete/{personId}")
    public String deletePerson(@PathVariable long personId, RedirectAttributes redirectAttributes) {
        personService.delete(personId);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec smazán");

        return "redirect:/person";
    }


    /**
     * This method handles exceptions of type PersonNotFoundException, which are thrown
     * when an insured person is not found in the system.
     *
     * @param redirectAttributes An object that allows adding flash attributes, which are used to convey
     *                           error messages to the template after redirection.
     * @return A redirect path to the listing page of insured persons, along with an error message indicating
     * that the requested insured person was not found.
     */
    @ExceptionHandler({PersonNotFoundException.class})
    public String handlePersonNotFoundException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen");

        return "redirect:/person";
    }

}
