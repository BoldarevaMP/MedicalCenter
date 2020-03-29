package unicorn.service.api;

import unicorn.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAll();

    void create(PatientDTO patientDTO);

    void update(PatientDTO patientDTO);

    PatientDTO getById(Integer id);

}
