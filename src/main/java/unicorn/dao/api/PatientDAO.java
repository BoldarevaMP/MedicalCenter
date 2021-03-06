package unicorn.dao.api;

import unicorn.entity.Patient;

import java.util.List;

public interface PatientDAO extends GenericDAO<Patient> {

    List<Patient> getPatientByLastName(String name);

    List<Patient> getPatientsByLikeName(String name);

    List<Patient> getAllSorted();
}
