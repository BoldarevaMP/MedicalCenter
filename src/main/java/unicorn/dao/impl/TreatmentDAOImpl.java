package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.TreatmentDAO;
import unicorn.dto.TreatmentDTO;

import unicorn.entity.Treatment;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository
public class TreatmentDAOImpl extends GenericDAOImpl<Treatment> implements TreatmentDAO {

    @PersistenceContext
    EntityManager entityManager;

    private List<TreatmentDTO> treatments = Arrays.asList(new TreatmentDTO(1, "pain"), new TreatmentDTO(2, "headache"));


    public List<TreatmentDTO> getAllTreatments(){
        return treatments;
    }
}
