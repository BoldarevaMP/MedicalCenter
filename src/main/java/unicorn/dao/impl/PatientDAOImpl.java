package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.PatientDAO;
import unicorn.entity.Patient;

@Repository
public class PatientDAOImpl extends GenericDAOImpl<Patient> implements PatientDAO {
}
