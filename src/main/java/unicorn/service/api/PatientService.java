package unicorn.service.api;

import unicorn.dto.PatientDTO;
import unicorn.entity.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAll();

    void create(PatientDTO patientDTO);

    void update(PatientDTO patientDTO);

    PatientDTO getById(Integer id);

    List<PatientDTO> getPatientByLastName(String name);

    List <PatientDTO> getByLikeName (String name);
}
