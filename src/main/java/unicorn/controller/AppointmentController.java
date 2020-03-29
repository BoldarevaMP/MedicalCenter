package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.EventDTO;
import unicorn.dto.UserDTO;
import unicorn.service.api.AppointmentService;
import unicorn.service.impl.AppointmentServiceImpl;

import java.util.List;

@Controller
//@RequestMapping(value = "/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = {"/addAppointment" }, method = RequestMethod.GET)
    public String addAppointment(ModelMap model) {
        model.addAttribute("appointment", new AppointmentDTO());
        return "appointment";
    }

    @RequestMapping(value = "/addAppointment", method = RequestMethod.POST)
    public String registration(@ModelAttribute("appointment") AppointmentDTO appointment, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
            return "appointment";
        }
        appointmentService.create(appointment);
        return "redirect:/welcome";
    }
}
