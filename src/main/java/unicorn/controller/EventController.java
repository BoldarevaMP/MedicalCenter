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

/**
 * Process requests related with events
 */

@Controller
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventValidator eventValidator;

    /**
     * Displays events list
     *
     * @param request - object for providing request information for HTTP servlets
     * @param model   - model for view
     * @return page with events list
     */

    @GetMapping(value = {"/list/all"})
    public String listEvents(HttpServletRequest request, ModelMap model) {
        List<EventDTO> events = eventService.getAll();
        model.addAttribute("events", convertToPageable(request, events));
        return "eventList";
    }

    /**
     * Displays today events list
     *
     * @param request - object for providing request information for HTTP servlets
     * @param model   - model for view
     * @return page with today events list
     */

    @GetMapping(value = {"/list/today"})
    public String listEventsToday(HttpServletRequest request, ModelMap model) {
        List<EventDTO> events = eventService.getEventsByDateToday();
        model.addAttribute("events", convertToPageable(request, events));
        return "eventListToday";
    }

    /**
     * Displays this hour events list
     *
     * @param request - object for providing request information for HTTP servlets
     * @param model   - model for view
     * @return page with this hour events list
     */

    @GetMapping(value = {"/list/hour"})
    public String listEventsThisHour(HttpServletRequest request, ModelMap model) {
        List<EventDTO> events = eventService.getEventsByDateHour();
        model.addAttribute("events", convertToPageable(request, events));
        return "eventListThisHour";
    }

    /**
     * Displays patients list
     *
     * @param model    - model for view
     * @param lastName - patients' last name
     * @return page with patients list
     */

    @GetMapping(value = {"/patient-name"})
    public String listPatientByLastName(ModelMap model, @RequestParam String lastName) {
        model.addAttribute("patients", eventService.getPatientsByLastName(lastName));
        return "patientByName";

    }

    /**
     * Provides list of patients
     *
     * @param name - patients' last name
     * @return list of patients
     */

    @GetMapping(value = {"/getPatientsByName"})
    public @ResponseBody
    List<PatientDTO> getPatients(@RequestParam String name) {
        return eventService.getPatientsByLikeName(name);
    }

    /**
     * Displays events list of concrete patient
     *
     * @param model - model for view
     * @param id    - patient id
     * @return - page with events list of concrete patient
     */

    @GetMapping(value = {"/patient-{id}"})
    public String listEventsPatient(ModelMap model, @PathVariable Integer id) {
        List<EventDTO> events = eventService.getEventsByPatientId(id);
        model.addAttribute("events", events);
        return "eventListPatient";

    }

    /**
     * Gets event data from DB and displays form for editing
     *
     * @param id    - id of editing appointment
     * @param model - model for view
     * @return page for event editing
     */

    @GetMapping(value = {"/edit-event-{id}"})
    public String editEvent(@PathVariable Integer id, ModelMap model) {
        if (!model.containsAttribute("event")) {
            EventDTO eventDTO = eventService.getByID(id);
            model.addAttribute("event", eventDTO);
        }
        model.addAttribute("edit", true);
        return "event";
    }

    /**
     * Updates event data
     *
     * @param eventDTO           - data for updating
     * @param bindingResult      - object for keeping errors
     * @param redirectAttributes - attribute for redirect to concrete page
     * @return url for redirect to event list
     */

    @PostMapping(value = {"/edit-event-{id}"})
    public String updateEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        eventValidator.validate(eventDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", bindingResult);
            redirectAttributes.addFlashAttribute("event", eventDTO);
            redirectAttributes.addAttribute("id", eventDTO.getId());
            return "redirect:/event/edit-event-{id}";
        }
        eventService.updateStatusAndComment(eventDTO);
        return "redirect:/event/list/all";
    }
}

