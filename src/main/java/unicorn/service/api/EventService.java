package unicorn.service.api;


import unicorn.dto.EventDTO;
import unicorn.dto.EventRestDTO;
import unicorn.dto.PatientDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAll();

    EventDTO getByID(Integer id);

    List<EventDTO> getEventsByDateHour();

    List<EventDTO> getEventsByDateToday();

    List<EventRestDTO> getEventsByDateTodayAfterNow();

    List<EventDTO> getEventsByPatientId(Integer id);

    void updateStatusAndComment(EventDTO eventDTO);

    List<PatientDTO> getPatientsByLastName(String name);

    List<PatientDTO> getPatientsByLikeName(String name);
}
