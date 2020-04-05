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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventValidator eventValidator;

    @RequestMapping(value = {"/list/all"}, method = RequestMethod.GET)
    public String listEvents(ModelMap model) {
        List<EventDTO> events = eventService.getAll();
        model.addAttribute("events", events);
        return "eventList";
    }

    @RequestMapping(value = {"/list/today"}, method = RequestMethod.GET)
    public String listEventsToday(ModelMap model) {
        List<EventDTO> events = eventService.getEventsByDateToday();
        model.addAttribute("events", events);
        return "eventListToday";
    }

    @RequestMapping(value = {"/list/thishour"}, method = RequestMethod.GET)
    public String listEventsThisHour(ModelMap model) {
        List<EventDTO> events = eventService.getEventsByDateHour();
        model.addAttribute("events", events);
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

    @RequestMapping(value = {"/addEvent"}, method = RequestMethod.GET)
    public String createEvent(ModelMap model) {
        EventDTO eventDTO = new EventDTO();
        model.addAttribute("event", eventDTO);
        model.addAttribute("edit", false);
        return "event";
    }

    @RequestMapping(value = {"/addEvent"}, method = RequestMethod.POST)
    public String createEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "event";
        }
        eventService.create(eventDTO);
        model.addAttribute("success", "Event is saved successfully");
        return "eventAdded";
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
    public String updateEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult result,
                              ModelMap model, RedirectAttributes redirectAttributes) {
        eventValidator.validate(eventDTO, result);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);
            redirectAttributes.addFlashAttribute("event", eventDTO);
            redirectAttributes.addAttribute("id", eventDTO.getId());
            return "redirect:/event/edit-event-{id}";
        }

        eventService.updateStatusAndComment(eventDTO);
        model.addAttribute("success", "Event was updated successfully");
        return "eventAdded";
    }
}

