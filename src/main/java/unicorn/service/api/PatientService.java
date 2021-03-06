package unicorn.service.api;

import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.entity.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAll();

    PatientDTO create(PatientDTO patientDTO);

    PatientDTO getById(Integer id);

    void changePatientStatusToDischarge(Patient patient);

    List<AppointmentDTO> getAppointmentsByPatientId(Integer id);
}
