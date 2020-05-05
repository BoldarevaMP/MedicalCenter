package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.service.api.AppointmentService;
import unicorn.validator.AppointmentValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Process requests related with appointments
 */

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentValidator appointmentValidator;

    /**
     * Shows form for add appointment
     *
     * @param model   - model for view
     * @param session - object for keeping and using session attributes
     * @return page for adding appointment
     */

    @GetMapping(value = {"/patient/add-appointment"})
    public String addAppointment(ModelMap model, HttpSession session) {
        if (!model.containsAttribute("appointment")) {
            AppointmentDTO appointment = new AppointmentDTO();
            appointment.setPatientDTO((PatientDTO) session.getAttribute("patient"));
            model.addAttribute("appointment", appointment);
        }
        return "appointment";
    }

    /**
     * Saves patient data
     *
     * @param appointmentDTO     - data for saving
     * @param bindingResult      - object for keeping errors
     * @param session            - object for keeping and using session attributes
     * @param redirectAttributes - attribute for redirect to concrete page
     * @return url for redirecting to concrete patient page
     */

    @PostMapping(value = "/patient/add-appointment")
    public String addAppointment(@Valid @ModelAttribute("appointment") AppointmentDTO appointmentDTO,
                                 BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        appointmentValidator.validate(appointmentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            return "redirect:/patient/add-appointment";
        }
        appointmentDTO.setPatientDTO((PatientDTO) session.getAttribute("patient"));
        appointmentService.create(appointmentDTO);
        redirectAttributes.addAttribute("id", appointmentDTO.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    /**
     * Changes appointment status from active to cancelled
     *
     * @param id                 - id of editing appointment
     * @param redirectAttributes - attribute for redirect to concrete page
     * @return url for redirecting to concrete patient page
     */

    @GetMapping(value = {"/patient/delete-appointment-{id}"})
    public String deleteAppointment(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        AppointmentDTO appointmentDTO = appointmentService.getById(id);
        appointmentService.changeStatusToCancelledById(id);
        redirectAttributes.addAttribute("id", appointmentDTO.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    /**
     * Gets appointment data from DB and displays form for editing
     *
     * @param id    - id of editing appointment
     * @param model - model for view
     * @return page for appointment editing
     */

    @GetMapping(value = {"/patient/edit-appointment-{id}"})
    public String editAppointment(@PathVariable Integer id, Model model) {
        if (!model.containsAttribute("appointment")) {
            AppointmentDTO appointmentDTO = appointmentService.getById(id);
            model.addAttribute("appointment", appointmentDTO);
        }
        model.addAttribute("edit", true);
        return "appointment";
    }

    /**
     * Updates appointment data
     *
     * @param appointmentDTO     - data for updating
     * @param bindingResult      - object for keeping errors
     * @param redirectAttributes - attribute for redirect to concrete page
     * @return url for redirect to concrete patient page
     */

    @PostMapping(value = {"/patient/edit-appointment-{id}"})
    public String updateAppointment(@Valid @ModelAttribute("appointment") AppointmentDTO appointmentDTO,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        appointmentValidator.validate(appointmentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            redirectAttributes.addAttribute("id", appointmentDTO.getId());
            return "redirect:/patient/edit-appointment-{id}";
        }
        appointmentService.update(appointmentDTO);
        redirectAttributes.addAttribute("id", appointmentDTO.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    /**
     * Provides list of treatments
     *
     * @param name - treatment name
     * @return list of treatments
     */

    @GetMapping(value = {"/getTreatmentByName"})
    public @ResponseBody
    List<TreatmentDTO> getTreatments(@RequestParam String name) {
        return appointmentService.getTreatmentByLikeNames(name);
    }
}
