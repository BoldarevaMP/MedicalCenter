package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.service.api.PatientService;
import unicorn.validator.PatientValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static unicorn.converter.PageableConverter.convertToPageable;

/**
 * Process requests related with patients.
 */

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientValidator patientValidator;


    /**
     * Displays patients list
     *
     * @param request - object for providing request information for HTTP servlets
     * @param model   - model for view
     * @return page with patients list
     */

    @GetMapping(value = {"/list"})
    public String listEvents(HttpServletRequest request, ModelMap model) {
        List<PatientDTO> patients = patientService.getAll();
        model.addAttribute("patients", convertToPageable(request, patients));
        return "patientList";
    }

    /**
     * Shows form for add patient.
     *
     * @param model - model for view
     * @return page with form for adding patient
     */

    @GetMapping(value = {"/add-patient"})
    public String newPatient(ModelMap model) {
        model.addAttribute("patient", new PatientDTO());
        model.addAttribute("edit", false);
        return "patient";
    }

    /**
     * Saves patient data
     *
     * @param patientDTO         - data for save
     * @param bindingResult      - object for keeping errors
     * @param redirectAttributes - attribute for redirect to concrete page
     * @return url for redirect to concrete patient page
     */

    @PostMapping(value = {"/add-patient"})
    public String savePatient(@Valid @ModelAttribute("patient") PatientDTO patientDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        patientValidator.validate(patientDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "patient";
        }
        PatientDTO patient = patientService.create(patientDTO);
        redirectAttributes.addAttribute("id", patient.getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    /**
     * Gets patient data from DB
     *
     * @param id      - id of editing patient
     * @param model   - model for view
     * @param session - object for keeping and using session attributes
     * @return page for patient editing
     */

    @GetMapping(value = {"/edit-patient-{id}"})
    public String editPatient(@PathVariable Integer id, ModelMap model, HttpSession session) {
        PatientDTO patientDTO = patientService.getById(id);
        List<AppointmentDTO> appointmentDTOList = patientService.getAppointmentsByPatientId(id);
        model.addAttribute("patient", patientDTO);
        model.addAttribute("edit", true);
        model.addAttribute("appointments", appointmentDTOList);
        session.setAttribute("patient", patientDTO);
        return "patientEdit";
    }
}
