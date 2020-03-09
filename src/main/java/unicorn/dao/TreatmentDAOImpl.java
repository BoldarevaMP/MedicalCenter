package unicorn.dao;

import org.springframework.stereotype.Repository;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Treatment;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository
public class TreatmentDAOImpl implements TreatmentDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Treatment treatment) {
        entityManager.persist(treatment);
    }

    private List<TreatmentDTO> treatments = Arrays.asList(new TreatmentDTO(1, "pain"), new TreatmentDTO(2, "headache"));


    public List<TreatmentDTO> getAllTreatments(){
        return treatments;
    }
}
