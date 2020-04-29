package app.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dao.api.TreatmentDAO;
import unicorn.entity.enums.AppointmentStatus;
import unicorn.message.MessageSender;
import unicorn.service.api.PatientService;
import unicorn.service.impl.AppointmentServiceImpl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static app.tests.DataInit.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
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
        DataInit.setUp();
        appointmentService.setMapper(mapper);
    }

    @Test
    public void testCreateAppointment() {
        when(treatmentDAO.getByName(appointment.getTreatment().getName())).thenReturn(treatment);
        doNothing().when(Mockito.spy(appointmentDAO)).create(appointment);
        doNothing().when(Mockito.spy(appointmentDAO)).update(appointment);
        doNothing().when(Mockito.spy(eventDAO)).create(event);
        doNothing().when(Mockito.spy(messageSender)).send(anyString());
        appointmentService.create(appointmentDTO);
    }

    @Test
    public void testUpdateAppointment() {
        when(patientDAO.getById(patient.getId())).thenReturn(appointment.getPatient());
        when(eventDAO.getPlannedEventsByAppointmentId(appointment.getId()))
                .thenReturn(Stream.of(event).collect(Collectors.toList()));
        doNothing().when(Mockito.spy(patientService)).changePatientStatusToDischarge(patient);
        when(appointmentDAO.getById(appointment.getId())).thenReturn(appointment);
        when(treatmentDAO.getByName(appointment.getTreatment().getName())).thenReturn(treatment);
        doNothing().when(Mockito.spy(appointmentDAO)).update(appointment);
        doNothing().when(Mockito.spy(messageSender)).send(anyString());
        appointmentService.update(appointmentDTO);
    }

    @Test
    public void testChangeStatusToCancelledById(){
        when(eventDAO.getPlannedEventsByAppointmentId(appointment.getId()))
                .thenReturn(Stream.of(event).collect(Collectors.toList()));
        when(appointmentDAO.getById(appointment.getId())).thenReturn(appointment);
        when(patientDAO.getById(appointment.getPatientId())).thenReturn(patient);
        doNothing().when(Mockito.spy(appointmentDAO)).update(appointment);
        doNothing().when(Mockito.spy(patientService)).changePatientStatusToDischarge(patient);
        doNothing().when(Mockito.spy(messageSender)).send(anyString());
        appointmentService.changeStatusToCancelledById(appointment.getPatientId());
        assertEquals(AppointmentStatus.CANCELLED, appointment.getStatus());
    }

    @Test
    public void testGetAppointmentById(){
        when(appointmentDAO.getById(appointment.getId())).thenReturn(appointment);
        assertEquals(appointmentService.getById(appointment.getId()).getId(), appointment.getId());
    }

    @Test
    public void testGetTreatmentByLikeNames(){
        when(treatmentDAO.getByLikeName(treatment.getName()))
                .thenReturn(Stream.of(treatment).collect(Collectors.toList()));
        assertEquals(appointmentService.getTreatmentByLikeNames(treatment.getName()).get(0).getId(),treatment.getId());

    }
}
