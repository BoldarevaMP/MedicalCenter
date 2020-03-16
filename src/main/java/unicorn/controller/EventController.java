package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import unicorn.dto.EventDTO;
import unicorn.service.api.EventService;
import java.util.List;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allEvents(Model model) {
        List<EventDTO> events = eventService.getAll();
        model.addAttribute("eventsList", events);
        return "event";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("id") Integer id, Model model) {
        EventDTO event = eventService.getByID(id);
         model.addAttribute("event", event);
        return "editEvent";
    }

}
