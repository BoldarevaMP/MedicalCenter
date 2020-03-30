package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.EventDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Appointment;
import unicorn.entity.Event;
import unicorn.service.api.EventService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {


    private static final Logger logger = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<EventDTO> getAllPlanned() {
        List<Event> eventList = eventDAO.getAllPlanned();
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventList.get(i).getId());
            eventDTO.setDate(eventList.get(i).getDate());
            eventDTO.setStatus(eventList.get(i).getStatus());
            eventDTO.setComment(eventList.get(i).getComment());

            AppointmentDTO appointmentDTO = new AppointmentDTO();
            Appointment appointment = eventList.get(i).getAppointment();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDosage(appointment.getDosage());
            appointmentDTO.setTreatmentDTO(modelMapper.map(appointment.getTreatment(), TreatmentDTO.class));
            appointmentDTO.setPatientDTO(modelMapper.map(appointment.getPatient(), PatientDTO.class));
            eventDTO.setAppointmentDTO(appointmentDTO);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EventDTO> getAll() {
        List<Event> eventList = eventDAO.getAllSorted();
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventList.get(i).getId());
            eventDTO.setDate(eventList.get(i).getDate());
            eventDTO.setStatus(eventList.get(i).getStatus());
            eventDTO.setComment(eventList.get(i).getComment());

            AppointmentDTO appointmentDTO = new AppointmentDTO();
            Appointment appointment = eventList.get(i).getAppointment();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDosage(appointment.getDosage());
            appointmentDTO.setTreatmentDTO(modelMapper.map(appointment.getTreatment(), TreatmentDTO.class));
            appointmentDTO.setPatientDTO(modelMapper.map(appointment.getPatient(), PatientDTO.class));
            eventDTO.setAppointmentDTO(appointmentDTO);
            eventDTOList.add(eventDTO);

        }

        return eventDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public EventDTO getByID(Integer id) {
        Event event = eventDAO.getById(id);
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setDate(event.getDate());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setComment(event.getComment());

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment appointment = event.getAppointment();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDosage(appointment.getDosage());
        appointmentDTO.setTreatmentDTO(modelMapper.map(appointment.getTreatment(), TreatmentDTO.class));
        appointmentDTO.setPatientDTO(modelMapper.map(appointment.getPatient(), PatientDTO.class));
        eventDTO.setAppointmentDTO(appointmentDTO);

        return eventDTO;
    }

    @Override
    @Transactional
    public void update(EventDTO eventDTO) {
        Event event = eventDAO.getById(eventDTO.getId());
        if (event != null) {
            eventDAO.update(modelMapper.map(eventDTO, Event.class));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(EventDTO eventDTO) {
        eventDAO.create(modelMapper.map(eventDTO, Event.class));
    }

    @Override
    @Transactional
    public void delete(EventDTO eventDTO) {
        eventDAO.delete(modelMapper.map(eventDTO, Event.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByDateHour() {
        List<Event> eventList = eventDAO.getEventsByDateHour();
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventList.get(i).getId());
            eventDTO.setDate(eventList.get(i).getDate());
            eventDTO.setStatus(eventList.get(i).getStatus());
            eventDTO.setComment(eventList.get(i).getComment());

            AppointmentDTO appointmentDTO = new AppointmentDTO();
            Appointment appointment = eventList.get(i).getAppointment();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDosage(appointment.getDosage());
            appointmentDTO.setTreatmentDTO(modelMapper.map(appointment.getTreatment(), TreatmentDTO.class));
            appointmentDTO.setPatientDTO(modelMapper.map(appointment.getPatient(), PatientDTO.class));
            eventDTO.setAppointmentDTO(appointmentDTO);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;

    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByDateToday() {
        List<Event> eventList = eventDAO.getEventsByDateToday();
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventList.get(i).getId());
            eventDTO.setDate(eventList.get(i).getDate());
            eventDTO.setStatus(eventList.get(i).getStatus());
            eventDTO.setComment(eventList.get(i).getComment());

            AppointmentDTO appointmentDTO = new AppointmentDTO();
            Appointment appointment = eventList.get(i).getAppointment();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDosage(appointment.getDosage());
            appointmentDTO.setTreatmentDTO(modelMapper.map(appointment.getTreatment(), TreatmentDTO.class));
            appointmentDTO.setPatientDTO(modelMapper.map(appointment.getPatient(), PatientDTO.class));
            eventDTO.setAppointmentDTO(appointmentDTO);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStatusAndComment(EventDTO eventDTO) {
        Event event = eventDAO.getById(eventDTO.getId());
        event.setStatus(eventDTO.getStatus());
        event.setComment(eventDTO.getComment());
        eventDAO.update(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> getEventsByPatientId(Integer id) {
        List<Event> eventList = eventDAO.getEventsByPatientId(id);
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventList.get(i).getId());
            eventDTO.setDate(eventList.get(i).getDate());
            eventDTO.setStatus(eventList.get(i).getStatus());
            eventDTO.setComment(eventList.get(i).getComment());

            AppointmentDTO appointmentDTO = new AppointmentDTO();
            Appointment appointment = eventList.get(i).getAppointment();
            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDosage(appointment.getDosage());
            appointmentDTO.setTreatmentDTO(modelMapper.map(appointment.getTreatment(), TreatmentDTO.class));
            appointmentDTO.setPatientDTO(modelMapper.map(appointment.getPatient(), PatientDTO.class));
            eventDTO.setAppointmentDTO(appointmentDTO);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }
}