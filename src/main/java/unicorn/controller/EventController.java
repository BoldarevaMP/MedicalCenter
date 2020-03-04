package unicorn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import unicorn.dto.EventDTO;

@Controller
public class EventController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("helloWorld");
        modelAndView.addObject("event",event);
        return modelAndView;
    }

    private static EventDTO event;
    static {
        event = new EventDTO();
        event.setStatus("Hello");
        event.setId(1);
        event.setTimeOfTheDay("11:00 a.m.");

    }
}
