package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import unicorn.dto.PatientDTO;
import unicorn.service.api.PatientService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEvents(ModelMap model) {
        List<PatientDTO> patients = patientService.getAll();
        model.addAttribute("patients", patients);
        return "patientList";
    }


    @RequestMapping(value = {"/addPatient"}, method = RequestMethod.GET)
    public String newPatient(ModelMap model) {
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("patient", patientDTO);
        model.addAttribute("edit", false);
        return "patient";
    }

    @RequestMapping(value = {"/addPatient"},method = RequestMethod.POST)
    public String savePatient(@Valid @ModelAttribute("patient") PatientDTO patientDTO, BindingResult result , ModelMap model){
        if (result.hasErrors()) {
            return "patient";
        }
        patientService.create(patientDTO);
        model.addAttribute("success", "Patient was saved successfully.");
        return "patientAdded";
    }

    @RequestMapping(value = { "/edit-patient-{id}" }, method = RequestMethod.GET)
    public String editPatient(@PathVariable Integer id, ModelMap model) {
        PatientDTO patientDTO = patientService.getByID(id);
        model.addAttribute("patient", patientDTO);
        model.addAttribute("edit", true);
        return "patient";
    }

    @RequestMapping(value = { "/edit-patient-{id}" }, method = RequestMethod.POST)
    public String updatePatient(@Valid @ModelAttribute("patient") PatientDTO patientDTO, BindingResult result, ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "patient";
        }
        patientService.update(patientDTO);
        model.addAttribute("success", "Patient was updated successfully.");
        return "patientAdded";
    }
}
