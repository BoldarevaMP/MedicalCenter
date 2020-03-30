package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.PatientDAO;
import unicorn.entity.Patient;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PatientDAOImpl extends GenericDAOImpl<Patient> implements PatientDAO {

    @Override
    public List<Patient> getPatientByLastName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> treatmentRoot = criteriaQuery.from(Patient.class);

        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(treatmentRoot.get("lastName"), name));
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

