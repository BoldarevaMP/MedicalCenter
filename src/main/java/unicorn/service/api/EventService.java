package unicorn.service.api;


import unicorn.dto.EventDTO;
import unicorn.dto.EventRestDTO;
import unicorn.dto.PatientDTO;
import unicorn.entity.Event;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllPlanned();

    List<EventDTO> getAll();

    EventDTO getByID(Integer id);

    void update(EventDTO eventDTO);

    void create(EventDTO eventDTO);

    List<EventDTO> getEventsByDateHour();

    List<EventDTO> getEventsByDateToday();

    List<EventRestDTO> getEventsByDateTodayAfterNow();

    List<EventRestDTO> createEventRestDtoListBasedOnEventList(List<Event> evenList);

    List<EventDTO> getEventsByPatientId(Integer id);

    void updateStatusAndComment(EventDTO eventDTO);

    List<PatientDTO> getPatientsByLastName(String name);

    List<PatientDTO> getPatientsByLikeName(String name);
}
