package app.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dao.api.TreatmentDAO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.enums.AppointmentStatus;
import unicorn.message.MessageSender;
import unicorn.service.api.PatientService;
import unicorn.service.impl.AppointmentServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static app.tests.DataInit.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {

    @Mock
    private EventDAO eventDAO;

    @Mock
    private TreatmentDAO treatmentDAO;

    @Mock
    private PatientDAO patientDAO;

    @Mock
    private AppointmentDAO appointmentDAO;

    @Mock
    private MessageSender messageSender;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @InjectMocks
    private ModelMapper mapper;

    @Before
    public void setUp() {
        DataInit.setUpTreatment();
        DataInit.setUpTreatmentDTO();
        DataInit.setUpPatient();
        DataInit.setUpPatientDTO();
        DataInit.setUpAppointmentDTO();
        DataInit.setUpAppointment();
        DataInit.setUpEventDTO();
        DataInit.setUpEvent();
        appointmentService.setMapper(mapper);
    }

    @Test
    public void testCreateAppointment() {
        when(treatmentDAO.getByName(appointmentDTO.getTreatmentDTO().getName())).thenReturn(treatment);
        appointmentService.create(appointmentDTO);
        assertEquals(AppointmentStatus.ACTIVE, appointmentDTO.getStatus());
    }

    @Test
    public void testUpdateAppointment() {
        when(patientDAO.getById(patient.getId())).thenReturn(appointment.getPatient());
        when(eventDAO.getPlannedEventsByAppointmentId(appointment.getId()))
                .thenReturn(Stream.of(event).collect(Collectors.toList()));
        when(appointmentDAO.getById(appointment.getId())).thenReturn(appointment);
        when(treatmentDAO.getByName(appointment.getTreatment().getName())).thenReturn(treatment);
        appointmentService.update(appointmentDTO);
        verify(appointmentDAO, atLeastOnce()).update(appointment);
        verify(messageSender, atLeastOnce()).send("Update");
    }

    @Test
    public void testChangeStatusToCancelledById() {
        when(eventDAO.getPlannedEventsByAppointmentId(appointment.getId()))
                .thenReturn(Stream.of(event).collect(Collectors.toList()));
        when(appointmentDAO.getById(appointment.getId())).thenReturn(appointment);
        when(patientDAO.getById(appointment.getPatientId())).thenReturn(patient);
        appointmentService.changeStatusToCancelledById(appointment.getPatientId());
        assertEquals(AppointmentStatus.CANCELLED, appointment.getStatus());
    }

    @Test
    public void testGetAppointmentById() {
        when(appointmentDAO.getById(appointment.getId())).thenReturn(appointment);
        assertEquals(appointmentService.getById(appointment.getId()).getId(), appointment.getId());
    }

    @Test
    public void testGetTreatments() {
        when(treatmentDAO.getAll()).thenReturn(Collections.EMPTY_LIST);
        List<TreatmentDTO> treatmentList = appointmentService.getAllTreatments();
        assertEquals(Collections.EMPTY_LIST, treatmentList);
    }


    @Test
    public void testGetTreatmentByLikeNames() {
        when(treatmentDAO.getByLikeName(treatment.getName()))
                .thenReturn(Stream.of(treatment).collect(Collectors.toList()));
        assertEquals(appointmentService.getTreatmentByLikeNames(treatment.getName()).get(0).getId(), treatment.getId());
    }
}
