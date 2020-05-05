package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.EventDAO;
import unicorn.entity.Event;

import java.util.ArrayList;
import java.util.List;


@Repository
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    @Override
    public List<Event> getEventsByDateToday() {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events WHERE DATE(date) = " +
                "CURRENT_DATE AND DATE(date) < CURRENT_DATE + INTERVAL '1 day' ORDER BY date", Event.class).getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    @Override
    public List<Event> getEventsByDateTodayAfterNow() {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events WHERE date >= NOW()" +
                "AND date < (CURRENT_DATE + INTERVAL '1 day') ORDER BY date", Event.class).getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    @Override
    public List<Event> getEventsByDateHour() {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events WHERE date >= NOW() " +
                "AND date < (NOW() + INTERVAL '1 hour')", Event.class).getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    @Override
    public List<Event> getEventsByPatientId(Integer patId) {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events JOIN appointments a\n" +
                "ON events.appointment_id = a.id\n" +
                "WHERE a.patient_id =   :patId", Event.class).setParameter("patId", patId).getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    @Override
    public List<Event> getAllSorted() {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events ORDER BY date", Event.class)
                .getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    @Override
    public List<Event> getPlannedEventsByAppointmentId(Integer id) {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events WHERE appointment_id = :id " +
                "AND status LIKE 'PLANNED'", Event.class).setParameter("id", id).getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }
}
