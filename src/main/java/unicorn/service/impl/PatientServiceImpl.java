package unicorn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.PatientDAO;
import unicorn.dao.api.UserDAO;
import unicorn.dto.PatientDTO;
import unicorn.dto.UserDTO;
import unicorn.entity.Patient;
import unicorn.entity.User;
import unicorn.entity.enums.PatientStatus;
import unicorn.service.api.PatientService;
import unicorn.service.api.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private UserService userService;

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
            patientDTO.setStartDate(patient.getStartDate());
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
        UserDTO userDTO = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Patient patient = new Patient();

        patient.setId(patientDTO.getId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setHealthInsurance(patientDTO.getHealthInsurance());
        patient.setStartDate(LocalDate.now());
        patient.setStatus(PatientStatus.TREATED);
        patient.setDiagnosis(patientDTO.getDiagnosis());
        patient.setDoctor(mapper.map(userDTO,User.class));


        patientDAO.create(patient);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(PatientDTO patientDTO) {
        patientDAO.update(mapper.map(patientDTO, Patient.class));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getById(Integer id) {
        Patient patient = patientDAO.getById(id);
        PatientDTO patientDTO = new PatientDTO();
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
        return patientDTO;
    }


}
