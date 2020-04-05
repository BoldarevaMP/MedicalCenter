package unicorn.converter;

import unicorn.dto.EventDTO;
import unicorn.entity.Event;

public class EventConverter {
    public static void convertEventToEventDTO(Event event, EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setDate(event.getDate());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setComment(event.getComment());
    }
}
