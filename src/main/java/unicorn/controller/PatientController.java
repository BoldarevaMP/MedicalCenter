package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientValidator patientValidator;


    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEvents(HttpServletRequest request, ModelMap model) {
        List<PatientDTO> patients = patientService.getAll();
        model.addAttribute("patients", convertToPageable(request, patients));
        return "patientList";
    }


    @RequestMapping(value = {"/add-patient"}, method = RequestMethod.GET)
    public String newPatient(ModelMap model) {
        model.addAttribute("patient", new PatientDTO());
        model.addAttribute("edit", false);
        return "patient";
    }

    @RequestMapping(value = {"/add-patient"}, method = RequestMethod.POST)
    public String savePatient(@Valid @ModelAttribute("patient") PatientDTO patientDTO, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        patientValidator.validate(patientDTO, result);
        if (result.hasErrors()) {
            return "patient";
        }
        PatientDTO patient = patientService.create(patientDTO);
        redirectAttributes.addAttribute("id", patient.getId());
        return "redirect:/patient/edit-patient-{id}";
    }

    @RequestMapping(value = {"/edit-patient-{id}"}, method = RequestMethod.GET)
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
