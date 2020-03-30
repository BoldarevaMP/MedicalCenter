package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.TreatmentDAO;
import unicorn.entity.Treatment;
import unicorn.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TreatmentDAOImpl extends GenericDAOImpl<Treatment> implements TreatmentDAO {
    @Override
    public Treatment getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Treatment> criteriaQuery = criteriaBuilder.createQuery(Treatment.class);
        Root<Treatment> treatmentRoot = criteriaQuery.from(Treatment.class);

        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(treatmentRoot.get("name"), name));
        }
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Treatment> getByLikeName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Treatment> criteriaQuery = criteriaBuilder.createQuery(Treatment.class);
        Root<Treatment> treatmentRoot = criteriaQuery.from(Treatment.class);

        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().like(treatmentRoot.get("name"), "%"+name+"%"));
        }
        List<Treatment> list = entityManager.createQuery(criteriaQuery).getResultList();
        return list;
    }
}