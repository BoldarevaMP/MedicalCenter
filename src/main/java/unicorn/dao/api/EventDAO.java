package unicorn.dao.api;

import org.springframework.stereotype.Repository;
import unicorn.entity.Event;

import java.util.List;

@Repository
public interface EventDAO extends GenericDAO<Event> {

    List<Event> getEventsByDateToday();

    List<Event> getEventsByDateTodayAfterNow();

    List<Event> getEventsByDateHour();

    List<Event> getEventsByPatientId(Integer id);

    List<Event> getAllSorted();

    List<Event> getPlannedEventsByAppointmentId(Integer id);
}
