package unicorn.dao;


import org.springframework.stereotype.Repository;
import unicorn.entity.Event;
import unicorn.entity.enums.TimeOfTheDay;

import java.util.Date;
import java.util.List;

@Repository
public interface EventDAO extends GenericDAO<Event>{
    List<Event> getEventsbyDate (Date date);

    List<Event> getEventsbyPeriod (TimeOfTheDay time);

}
