package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.UserDAO;
import unicorn.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        if (email != null) {
            criteriaQuery.where(manager.getCriteriaBuilder().equal(userRoot.get("email"), email));
        }
        List<User> list = manager.createQuery(criteriaQuery).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
