package unicorn.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import unicorn.dao.api.EventDAO;
import unicorn.entity.Event;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    @Override
    public List<Event> getEventsByDateToday() {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events WHERE DATE(date) = CURRENT_DATE AND DATE(date) < CURRENT_DATE + INTERVAL '1 day' AND status LIKE 'PLANNED'", Event.class).getResultList();
        if (list.isEmpty()) {
            return new ArrayList<>();
        } else {
            return list;
        }
    }

    @Override
    public List<Event> getEventsByDateHour() {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events WHERE date >= NOW() AND date < (NOW() + INTERVAL '1 hour') AND status LIKE 'PLANNED'", Event.class).getResultList();
        if (list.isEmpty()) {
            return new ArrayList<>();
        } else {
            return list;
        }
    }

    @Override
    public List<Event> getAllPlanned() {
        TypedQuery<Event> query = entityManager.createQuery("FROM Event WHERE status LIKE 'PLANNED'", Event.class);
        List<Event> list = query.getResultList();
        return list.isEmpty()?new ArrayList<>():list;
    }

    @Override

    public List<Event> getEventsByPatientId(Integer patId) {
        List<Event> list = entityManager.createNativeQuery("SELECT * FROM events JOIN appointments a\n" +
                "ON events.appointment_id = a.id\n" +
                "WHERE a.patient_id =   :patId AND status LIKE 'PLANNED'", Event.class).setParameter("patId", patId).getResultList();

        return list.isEmpty()? null:list;
    }
}
