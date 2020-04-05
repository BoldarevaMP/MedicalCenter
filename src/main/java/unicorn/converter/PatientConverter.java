package unicorn.converter;

import unicorn.dto.PatientDTO;
import unicorn.dto.UserDTO;
import unicorn.entity.Patient;

public class PatientConverter {
    public static void convertPatientToPatientDTO(Patient patient, PatientDTO patientDTO) {
        patientDTO.setId(patient.getId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setHealthInsurance(patient.getHealthInsurance());
        patientDTO.setStatus(patient.getStatus());
        patientDTO.setStartDate(patient.getStartDate());
        patientDTO.setDiagnosis(patient.getDiagnosis());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(patient.getDoctor().getId());
        userDTO.setFirstName(patient.getDoctor().getFirstName());
        userDTO.setLastName(patient.getDoctor().getLastName());
        userDTO.setEmail(patient.getDoctor().getEmail());
        userDTO.setPassword(patient.getDoctor().getPassword());
        patientDTO.setDoctorDTO(userDTO);
    }

    public static void convertPatientDtoToPatient(PatientDTO patientDTO, Patient patient) {
        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setHealthInsurance(patientDTO.getHealthInsurance());
        patient.setDiagnosis(patientDTO.getDiagnosis());
        patient.setStartDate(patientDTO.getStartDate());
        patient.setStatus(patientDTO.getStatus());
    }
}
