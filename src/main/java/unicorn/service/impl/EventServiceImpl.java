package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.EventDAO;
import unicorn.dto.EventDTO;
import unicorn.entity.Event;
import unicorn.service.api.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {


    private static final Logger logger = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<EventDTO> getAll() {
        List<Event> eventList = eventDAO.getAll();
        if (eventList != null){
            return eventList.stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public EventDTO getByID(Integer id) {
        Event event = eventDAO.getById(id);
        if (event != null){
            return modelMapper.map(event, EventDTO.class);
        }
        return null;
    }
}