package unicorn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unicorn.dto.EventDTO;
import unicorn.dto.PatientDTO;
import unicorn.service.api.EventService;
import unicorn.validator.EventValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static unicorn.converter.PageableConverter.convertToPageable;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventValidator eventValidator;

    @RequestMapping(value = {"/list/all"}, method = RequestMethod.GET)
    public String listEvents(HttpServletRequest request, ModelMap model) {
        List<EventDTO> events = eventService.getAll();
        model.addAttribute("events", convertToPageable(request, events));
        return "eventList";
    }

    @RequestMapping(value = {"/list/today"}, method = RequestMethod.GET)
    public String listEventsToday(HttpServletRequest request, ModelMap model) {
        List<EventDTO> events = eventService.getEventsByDateToday();
        model.addAttribute("events", convertToPageable(request, events));
        return "eventListToday";
    }

    @RequestMapping(value = {"/list/thishour"}, method = RequestMethod.GET)
    public String listEventsThisHour(HttpServletRequest request, ModelMap model) {
        List<EventDTO> events = eventService.getEventsByDateHour();
        model.addAttribute("events", convertToPageable(request, events));
        return "eventListThisHour";
    }

    @RequestMapping(value = {"/patientName"}, method = RequestMethod.GET)
    public String listPatientByLastName(ModelMap model, @RequestParam String lastName) {
        model.addAttribute("patients", eventService.getPatientsByLastName(lastName));
        return "patientByName";

    }

    @RequestMapping(value = {"/getPatientsByName"}, method = RequestMethod.GET)
    public @ResponseBody
    List<PatientDTO> getPatients(@RequestParam String name) {
        return eventService.getPatientsByLikeName(name);
    }

    @RequestMapping(value = {"/patient-{id}"}, method = RequestMethod.GET)
    public String listEventsPatient(ModelMap model, @PathVariable Integer id) {
        List<EventDTO> events = eventService.getEventsByPatientId(id);
        model.addAttribute("events", events);
        return "eventListPatient";

    }

    @RequestMapping(value = {"/edit-event-{id}"}, method = RequestMethod.GET)
    public String editEvent(@PathVariable Integer id, ModelMap model) {
        if (!model.containsAttribute("event")) {
            EventDTO eventDTO = eventService.getByID(id);
            model.addAttribute("event", eventDTO);
        }
        model.addAttribute("edit", true);
        return "event";
    }

    @RequestMapping(value = {"/edit-event-{id}"}, method = RequestMethod.POST)
    public String updateEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult result, ModelMap model,
                              RedirectAttributes redirectAttributes) {
        eventValidator.validate(eventDTO, result);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);
            redirectAttributes.addFlashAttribute("event", eventDTO);
            redirectAttributes.addAttribute("id", eventDTO.getId());
            return "redirect:/event/edit-event-{id}";
        }
        eventService.updateStatusAndComment(eventDTO);
        return "redirect:/event/list/all";
    }
}

