package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.converter.EventConverter;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dto.*;
import unicorn.entity.Appointment;
import unicorn.entity.Event;
import unicorn.entity.Patient;
import unicorn.message.MessageSender;
import unicorn.service.api.EventService;
import unicorn.service.api.PatientService;

import javax.jms.MessageListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MessageSender messageSender;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(EventDTO eventDTO) {
        eventDAO.create(mapper.map(eventDTO, Event.class));
        logger.info("Event is created.");
    }

    @Override
    @Transactional
    public void update(EventDTO eventDTO) {
        Event event = eventDAO.getById(eventDTO.getId());
        if (event != null) {
            eventDAO.update(mapper.map(eventDTO, Event.class));
            messageSender.send("Update");
            logger.info("Event is updated.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStatusAndComment(EventDTO eventDTO) {
        Event event = eventDAO.getById(eventDTO.getId());
        Patient patient = patientDAO.getById(event.getPatientId());
        event.setStatus(eventDTO.getStatus());
        event.setComment(eventDTO.getComment());
        eventDAO.update(event);
        patientService.changePatientStatusToDischarge(patient);
        messageSender.send("Update");
        logger.info("Event is updated by nurse.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getAllPlanned() {
        List<Event> eventList = eventDAO.getAllPlanned();
        return createEventDtoListBasedOnEventList(eventList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getAll() {
        List<Event> eventList = eventDAO.getAllSorted();
        return createEventDtoListBasedOnEventList(eventList);
    }

    @Override
    @Transactional(readOnly = true)
    public EventDTO getByID(Integer id) {
        Event event = eventDAO.getById(id);
        EventDTO eventDTO = new EventDTO();
        EventConverter.convertEventToEventDTO(event, eventDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment appointment = event.getAppointment();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDosage(appointment.getDosage());
        appointmentDTO.setTreatmentDTO(mapper.map(appointment.getTreatment(), TreatmentDTO.class));
        appointmentDTO.setPatientDTO(mapper.map(appointment.getPatient(), PatientDTO.class));
        eventDTO.setAppointmentDTO(appointmentDTO);
        return eventDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByDateHour() {
        List<Event> eventList = eventDAO.getEventsByDateHour();
        return createEventDtoListBasedOnEventList(eventList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByDateToday() {
        List<Event> eventList = eventDAO.getEventsByDateToday();
        return createEventDtoListBasedOnEventList(eventList);
    }

    @Override
    @Transactional
    public List<EventRestDTO> createEventRestDtoListBasedOnEventDtoList(List<EventDTO> eventDTOList) {
        List<EventRestDTO> eventRestDTOList = new ArrayList<>();
        for (int i =0; i<eventDTOList.size(); i++){
            EventRestDTO eventRestDTO = new EventRestDTO();
            EventConverter.convertEventDtoToEventDtoRest(eventDTOList.get(i), eventRestDTO);
            eventRestDTOList.add(eventRestDTO);
        }

        return eventRestDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByPatientId(Integer id) {

        List<Event> eventList = eventDAO.getEventsByPatientId(id);
        return createEventDtoListBasedOnEventList(eventList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getPatientsByLastName(String lastName) {
        List<Patient> list = patientDAO.getPatientByLastName(lastName);
        return list.stream().map(patient -> mapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getPatientsByLikeName(String name) {
        List<Patient> list = patientDAO.getByLikeName(name);
        return list.stream().map(patient -> mapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }


    private List<EventDTO> createEventDtoListBasedOnEventList(List<Event> eventList) {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            EventDTO eventDTO = new EventDTO();
            EventConverter.convertEventToEventDTO(eventList.get(i), eventDTO);
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            Appointment appointment = eventList.get(i).getAppointment();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDosage(appointment.getDosage());
            appointmentDTO.setTreatmentDTO(mapper.map(appointment.getTreatment(), TreatmentDTO.class));
            appointmentDTO.setPatientDTO(mapper.map(appointment.getPatient(), PatientDTO.class));
            eventDTO.setAppointmentDTO(appointmentDTO);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }
}