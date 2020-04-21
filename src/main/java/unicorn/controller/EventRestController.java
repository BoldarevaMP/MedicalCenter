package unicorn.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import unicorn.dto.EventRestDTO;
import unicorn.service.api.EventService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class EventRestController {

    private static final Logger logger = Logger.getLogger(EventRestController.class);

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/events-today")
    public List<EventRestDTO> getEventsRestToday(){
        logger.info("Event list is sent");
        return eventService.createEventRestDtoListBasedOnEventDtoList(eventService.getEventsByDateToday());
    }
}
