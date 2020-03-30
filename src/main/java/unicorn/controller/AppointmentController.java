package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Treatment;
import unicorn.service.api.AppointmentService;
import unicorn.service.api.TreatmentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(value = {"/addAppointment"}, method = RequestMethod.GET)
    public String addAppointment(ModelMap model) {
        model.addAttribute("appointment", new AppointmentDTO());
        return "appointment";
    }

    @RequestMapping(value = "/addAppointment", method = RequestMethod.POST)
    public String addAppointment(@ModelAttribute("appointment") AppointmentDTO appointment,
                               BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "appointment";
        }
        appointment.setPatientDTO((PatientDTO) session.getAttribute("patient"));
        appointmentService.create(appointment);
        model.addAttribute("patId", appointment.getPatientDTO().getId());
        return "redirect:/patient/edit-patient-{patId}";
    }

    @RequestMapping(value = {"/getTreatmentByName"}, method = RequestMethod.GET)
    public @ResponseBody
    List<TreatmentDTO> getTreatments(@RequestParam String name) {
        return treatmentService.getByLikeNames(name);
    }
}
