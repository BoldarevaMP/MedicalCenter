package unicorn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.PatientDAO;
import unicorn.dto.PatientDTO;
import unicorn.dto.UserDTO;
import unicorn.entity.Patient;
import unicorn.service.api.PatientService;

import java.util.ArrayList;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getAll() {
        List<Patient> patientList = patientDAO.getAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setFirstName(patient.getFirstName());
            patientDTO.setLastName(patient.getLastName());
            patientDTO.setHealthInsurance(patient.getHealthInsurance());
            patientDTO.setStatus(patient.getStatus());
            patientDTO.setDiagnosis(patient.getDiagnosis());
            UserDTO userDTO = new UserDTO();
            userDTO.setId(patient.getDoctor().getId());
            userDTO.setFirstName(patient.getDoctor().getFirstName());
            userDTO.setLastName(patient.getDoctor().getLastName());
            userDTO.setEmail(patient.getDoctor().getEmail());
            userDTO.setPassword(patient.getDoctor().getPassword());
            patientDTO.setDoctorDTO(userDTO);
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(PatientDTO patientDTO) {
        patientDAO.create(mapper.map(patientDTO, Patient.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(PatientDTO patientDTO) {
        patientDAO.update(mapper.map(patientDTO, Patient.class));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getByID(Integer id) {
        Patient patient = patientDAO.getById(id);
        return mapper.map(patient, PatientDTO.class);
    }
}
