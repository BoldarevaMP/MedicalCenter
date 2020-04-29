package app.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dto.EventDTO;
import unicorn.dto.EventRestDTO;
import unicorn.dto.PatientDTO;
import unicorn.entity.enums.EventStatus;
import unicorn.message.MessageSender;
import unicorn.service.api.PatientService;
import unicorn.service.impl.EventServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static app.tests.DataInit.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EventServiceTest {

    @Mock
    private EventDAO eventDAO;

    @Mock
    private PatientDAO patientDAO;

    @Mock
    private PatientService patientService;

    @Mock
    private MessageSender messageSender;

    @InjectMocks
    private EventServiceImpl eventService;

    @InjectMocks
    private ModelMapper mapper;

    @Before
    public void setUp() {
        DataInit.setUp();
        eventService.setMapper(mapper);
    }

    @Test
    public void testUpdateEventStatusToDone() {
        when(eventDAO.getById(eventDTO.getId())).thenReturn(event);
        when(patientDAO.getById(event.getPatientId())).thenReturn(patient);
        doNothing().when(Mockito.spy(eventDAO)).update(event);
        doNothing().when(Mockito.spy(patientService)).changePatientStatusToDischarge(patient);
        doNothing().when(Mockito.spy(messageSender)).send(anyString());
        eventService.updateStatusAndComment(eventDTO);

        assertEquals(EventStatus.DONE, event.getStatus());
    }

    @Test
    public void testGetAllEvents() {
        when(eventDAO.getAllSorted()).thenReturn(Stream.of(event).collect(Collectors.toList()));
        List<EventDTO> eventDTOList = eventService.getAll();
        assertEquals(eventDTO.getId(), eventDTOList.get(0).getId());
    }

    @Test
    public void testGetEventsByDateToday() {
        when(eventDAO.getEventsByDateToday()).thenReturn(Stream.of(event).collect(Collectors.toList()));
        List<EventDTO> eventDTOList = eventService.getEventsByDateToday();
        assertEquals(eventDTO.getId(), eventDTOList.get(0).getId());
    }

    @Test
    public void testGetEventsByDateTodayAfterNow(){
        when(eventDAO.getEventsByDateTodayAfterNow()).thenReturn(Stream.of(event).collect(Collectors.toList()));
        List<EventRestDTO> eventRestDTOList = eventService.getEventsByDateTodayAfterNow();
        assertEquals(eventRestDTO.getId(), eventRestDTOList.get(0).getId());
    }

    @Test
    public void testGetEventsByPatientId() {
        when(eventDAO.getEventsByPatientId(patient.getId()))
                .thenReturn(Stream.of(event).collect(Collectors.toList()));
        List<EventDTO> eventDTOList = eventService.getEventsByPatientId(patient.getId());
        assertEquals(eventDTO.getId(), eventDTOList.get(0).getId());
    }

    @Test
    public void testGetPatientsByLastName() {
        when(patientDAO.getPatientByLastName(patient.getLastName()))
                .thenReturn(Stream.of(patient).collect(Collectors.toList()));
        List<PatientDTO> patientDTOList = eventService.getPatientsByLastName(patient.getLastName());
        assertEquals(patientDTO.getId(), patientDTOList.get(0).getId());
    }

    @Test
    public void testGetPatientsByLikeName() {
        when(patientDAO.getPatientsByLikeName(patient.getLastName()))
                .thenReturn(Stream.of(patient).collect(Collectors.toList()));
        List<PatientDTO> patientDTOList = eventService.getPatientsByLikeName(patient.getLastName());
        assertEquals(patientDTO.getId(), patientDTOList.get(0).getId());
    }

    @Test
    public void testGetEventById() {
        when(eventDAO.getById(eventDTO.getId())).thenReturn(event);
        assertEquals(eventService.getByID(event.getId()).getId(), event.getId());
    }

    @Test
    public void testGetEventsByDateHour() {
        when(eventDAO.getEventsByDateHour()).thenReturn(Stream.of(event).collect(Collectors.toList()));
        List<EventDTO> eventDTOList = eventService.getEventsByDateHour();
        assertEquals(eventDTO.getId(), eventDTOList.get(0).getId());
    }
}
