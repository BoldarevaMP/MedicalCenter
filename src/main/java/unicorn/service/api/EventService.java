package unicorn.service.api;


import unicorn.dto.EventDTO;

import java.util.List;

public interface EventService {
    List <EventDTO>getAll();

    EventDTO getByID(Integer id);

}
