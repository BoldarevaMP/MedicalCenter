package unicorn.dao.api;


import org.springframework.stereotype.Repository;
import unicorn.entity.Event;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventDAO extends GenericDAO<Event> {
    List<Event> getEventsByDate (LocalDate date);

    List<Event> getEventsByPeriod (String time);

}
