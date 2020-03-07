package unicorn.dao;

import org.springframework.stereotype.Repository;
import unicorn.entity.Event;
import unicorn.entity.enums.TimeOfTheDay;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO{

    @Override
    public List<Event> getEventsbyDate(Date date) {
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
    public List<Event> getEventsbyPeriod(TimeOfTheDay time) {
        return null;
    }
}
