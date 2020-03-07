package unicorn.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicorn.dao.EventDAO;
import unicorn.dao.EventDAOImpl;
import unicorn.dto.EventDTO;
import unicorn.entity.Event;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventDAO eventDAO;

    @Override
    public void create(EventDTO eventDTO) {
        if (eventDTO != null)
            eventDAO.create(modelMapper.map(eventDTO, Event.class));
    }

    @Override
    public void update(EventDTO eventDTO) {

    }

    @Override
    public void delete(EventDTO eventDTO) {

    }

    @Override
    public EventDTO getById(int id) {
        return null;
    }

    @Override
    public List<EventDTO> getAll() {
        return null;
    }

    @Override
    public List<EventDTO> getEventsByDate(Date date) {
        return null;
    }
}