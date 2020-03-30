package unicorn.service.api;


import unicorn.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllPlanned();

    List<EventDTO> getAll();

    EventDTO getByID(Integer id);

    void update(EventDTO eventDTO);

    void create(EventDTO eventDTO);

    void delete(EventDTO eventDTO);

    List<EventDTO> getEventsByDateHour();

    List<EventDTO> getEventsByDateToday();

    List<EventDTO> getEventsByPatientId(Integer id);

    void updateStatusAndComment(EventDTO eventDTO);
}
