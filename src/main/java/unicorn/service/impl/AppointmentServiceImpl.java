package unicorn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Appointment;
import unicorn.service.api.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(AppointmentDTO appointmentDTO) {
        appointmentDAO.create(mapper.map(appointmentDTO, Appointment.class));
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDTO getById(Integer id) {
        Appointment appointment = appointmentDAO.getById(id);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDosage(appointment.getDosage());
        appointmentDTO.setPatientDTO(mapper.map(appointment.getPatient(), PatientDTO.class));
        appointmentDTO.setTreatmentDTO(mapper.map(appointment.getTreatment(), TreatmentDTO.class));
        return appointmentDTO;
    }
}
