package unicorn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import unicorn.dao.api.PatientDAO;
import unicorn.entity.Event;
import unicorn.entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PatientDAOImpl extends GenericDAOImpl<Patient> implements PatientDAO {


}
