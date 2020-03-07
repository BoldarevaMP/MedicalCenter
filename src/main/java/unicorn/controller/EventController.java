package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;;
import unicorn.dao.EventDAO;


import unicorn.entity.Event;

import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventDAO eventDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("helloWorld");
        //modelAndView.addObject("event",event);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody()
    public Event saveEvent(){
        Event event = new Event();
        event.setDate(new Date());
        eventDAO.create(event);
        return event;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Event getEvent(){
        List<Event> events=eventDAO.getAll();
        return eventDAO.getById(events.get(0).getId());
    }
}
