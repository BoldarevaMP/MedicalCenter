package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.converter.AppointmentConverter;
import unicorn.converter.PatientConverter;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.dto.UserDTO;
import unicorn.entity.Appointment;
import unicorn.entity.Event;
import unicorn.entity.Patient;
import unicorn.entity.User;
import unicorn.entity.enums.PatientStatus;
import unicorn.message.MessageSender;
import unicorn.service.api.PatientService;
import unicorn.service.api.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PatientDTO create(PatientDTO patientDTO) {
        UserDTO userDTO = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Patient patient = new Patient();
        patientDTO.setStartDate(LocalDate.now());
        patientDTO.setStatus(PatientStatus.TREATED);
        PatientConverter.convertPatientDtoToPatient(patientDTO, patient);
        patient.setDoctor(mapper.map(userDTO, User.class));
        patientDAO.create(patient);
        patientDTO.setId(patient.getId());
        logger.info("Patient is created.");
        return patientDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(PatientDTO patientDTO) {
        patientDAO.update(mapper.map(patientDTO, Patient.class));
        logger.info("Patient is updated.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getAll() {
        List<Patient> patientList = patientDAO.getAllSorted();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            PatientDTO patientDTO = new PatientDTO();
            PatientConverter.convertPatientToPatientDTO(patient, patientDTO);
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getById(Integer id) {
        Patient patient = patientDAO.getById(id);
        PatientDTO patientDTO = new PatientDTO();
        PatientConverter.convertPatientToPatientDTO(patient, patientDTO);
        return patientDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> getAppointmentsByPatientId(Integer id) {
        List<Appointment> appointmentList = appointmentDAO.getByPatientId(id);
        List<AppointmentDTO> appointmentDtoList = new ArrayList<>();
        for (int i = 0; i < appointmentList.size(); i++) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            AppointmentConverter.converterAppointmentToAppointmentDTO(appointmentList.get(i), appointmentDTO);
            appointmentDTO.setPatientDTO(mapper.map(appointmentList.get(i).getPatient(), PatientDTO.class));
            appointmentDTO.setTreatmentDTO(mapper.map(appointmentList.get(i).getTreatment(), TreatmentDTO.class));
            appointmentDtoList.add(appointmentDTO);
        }
        return appointmentDtoList;
    }

    public void changePatientStatusToDischarge(Patient patient) {
        List<Appointment> appointmentList = appointmentDAO.getByPatientId(patient.getId());
        boolean isTreated = false;
        for (int i = 0; i < appointmentList.size(); i++) {
            List<Event> eventListPlanned = eventDAO.getPlannedEventsByAppointmentId(appointmentList.get(i).getId());
            if (eventListPlanned.size() != 0) {
                isTreated = true;
                break;
            }
        }
        if (!isTreated) {
            patient.setStatus(PatientStatus.DISCHARGED);
            patientDAO.update(patient);
            logger.info("Patient is discharged.");
        }
    }
}
