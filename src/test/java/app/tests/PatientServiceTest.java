package app.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.entity.Patient;
import unicorn.entity.enums.PatientStatus;
import unicorn.service.api.UserService;
import unicorn.service.impl.PatientServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static app.tests.DataInit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PatientServiceTest {

    @Mock
    private PatientDAO patientDAO;

    @Mock
    private AppointmentDAO appointmentDAO;

    @Mock
    private EventDAO eventDAO;

    @Mock
    private UserService userService;


    @InjectMocks
    private PatientServiceImpl patientService;

    @InjectMocks
    private ModelMapper mapper;

    @Before
    public void setUp() {
        DataInit.setUp();
        patientService.setMapper(mapper);
    }

    @Test
    public void testCreate() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn(userDTO.getEmail());
        when(userService.getUserByEmail(userDTO.getEmail())).thenReturn(userDTO);
        doNothing().when(Mockito.spy(patientDAO)).create(patient);
        PatientDTO patientDtoTest = patientService.create(patientDTO);
        assertNotNull(patientDtoTest);
        assertEquals(patientDTO.getId(),patientDtoTest.getId());
    }

    @Test
    public void testGetAllPatients(){
        when(patientDAO.getAllSorted()).thenReturn(Stream.of(patient).collect(Collectors.toList()));
        List<PatientDTO> patientDTOList = patientService.getAll();
        assertEquals(patientDTO.getId(), patientDTOList.get(0).getId());
    }

    @Test
    public void testGetPatientById(){
        when(patientDAO.getById(patient.getId())).thenReturn(patient);
        assertEquals(patientService.getById(patient.getId()).getId(), patientDTO.getId());
    }

    @Test
    public void testGetAppointmentsByPatientId(){
        when(appointmentDAO.getByPatientId(patient.getId())).thenReturn(Stream.of(appointment).collect(Collectors.toList()));
        List<AppointmentDTO> appointmentDTOList = patientService.getAppointmentsByPatientId(patient.getId());
        assertEquals(appointmentDTO.getId(), appointmentDTOList.get(0).getId());
    }

    @Test
    public void testChangePatientStatusToDischarge(){
        when(appointmentDAO.getByPatientId(patient.getId())).thenReturn(Stream.of(appointment).collect(Collectors.toList()));
        patientService.changePatientStatusToDischarge(patient);
        when(eventDAO.getPlannedEventsByAppointmentId(appointment.getId()))
                .thenReturn(Stream.of(event).collect(Collectors.toList()));
        assertEquals(PatientStatus.DISCHARGED, patient.getStatus());

    }

}
