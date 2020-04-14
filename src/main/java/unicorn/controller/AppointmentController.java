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


@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentValidator appointmentValidator;

    @RequestMapping(value = {"/patient/addAppointment"}, method = RequestMethod.GET)
    public String addAppointment(ModelMap model, HttpSession session) {
        if (!model.containsAttribute("appointment")) {
            AppointmentDTO appointment = new AppointmentDTO();
            appointment.setPatientDTO((PatientDTO) session.getAttribute("patient"));
            model.addAttribute("appointment", appointment);
        }
        return "appointment";
    }

    @RequestMapping(value = "/patient/addAppointment", method = RequestMethod.POST)
    public String addAppointment(@Valid @ModelAttribute("appointment") AppointmentDTO appointmentDTO, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        appointmentValidator.validate(appointmentDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            return "redirect:/patient/addAppointment";
        }
        appointmentDTO.setPatientDTO((PatientDTO) session.getAttribute("patient"));
        appointmentService.create(appointmentDTO);
        redirectAttributes.addAttribute("id", appointmentDTO.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    @RequestMapping(value = {"/patient/delete-appointment-{id}"}, method = RequestMethod.GET)
    public String deleteAppointment(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        AppointmentDTO appointmentDTO = appointmentService.getById(id);
        appointmentService.changeStatusToCancelledById(id);
        redirectAttributes.addAttribute("id", appointmentDTO.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    @RequestMapping(value = {"/patient/edit-appointment-{id}"}, method = RequestMethod.GET)
    public String editAppointment(@PathVariable Integer id, Model model) {
        if (!model.containsAttribute("appointment")) {
            AppointmentDTO appointmentDTO = appointmentService.getById(id);
            model.addAttribute("appointment", appointmentDTO);
        }
        model.addAttribute("edit", true);
        return "appointment";
    }

    @RequestMapping(value = {"/patient/edit-appointment-{id}"}, method = RequestMethod.POST)
    public String updateAppointment(@Valid @ModelAttribute("appointment") AppointmentDTO appointmentDTO, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        appointmentValidator.validate(appointmentDTO, result);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", result);
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            redirectAttributes.addAttribute("id", appointmentDTO.getId());
            return "redirect:/patient/edit-appointment-{id}";
        }
        appointmentService.update(appointmentDTO);
        redirectAttributes.addAttribute("id", appointmentDTO.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    @RequestMapping(value = {"/getTreatmentByName"}, method = RequestMethod.GET)
    public @ResponseBody
    List<TreatmentDTO> getTreatments(@RequestParam String name) {
        return appointmentService.getTreatmentByLikeNames(name);
    }
}
