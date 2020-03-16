package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.EventDAO;
import unicorn.entity.Event;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

import java.util.List;


@Repository
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    @Override
    public List<Event> getEventsByDate(LocalDate date) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        if (date != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(eventRoot.get("date"), date));
        }
        List<Event> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public List<Event> getEventsByPeriod(String time) {
        return null;
    }


}
