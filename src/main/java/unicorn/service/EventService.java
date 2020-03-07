package unicorn.service;

import unicorn.dao.EventDAO;
import unicorn.dto.EventDTO;

import java.util.Date;
import java.util.List;

public interface EventService {
    void create (EventDTO eventDTO);
    void update (EventDTO eventDTO);
    void delete (EventDTO eventDTO);
    EventDTO getById (int id);
    List<EventDTO> getAll();
    List<EventDTO>getEventsByDate(Date date);
}
