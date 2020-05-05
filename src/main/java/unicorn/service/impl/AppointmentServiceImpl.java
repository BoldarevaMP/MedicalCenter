package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.converter.AppointmentConverter;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dao.api.PatientDAO;
import unicorn.dao.api.TreatmentDAO;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Appointment;
import unicorn.entity.Event;
import unicorn.entity.Patient;
import unicorn.entity.Treatment;
import unicorn.entity.enums.AppointmentStatus;
import unicorn.entity.enums.DaysOfWeek;
import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TimeOfTheDay;
import unicorn.message.MessageSender;
import unicorn.service.api.AppointmentService;
import unicorn.service.api.PatientService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of service for appointment handling
 */

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = Logger.getLogger(AppointmentServiceImpl.class);
    private final String UPDATE = "Update";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private TreatmentDAO treatmentDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MessageSender messageSender;

    /**
     * Creates appointment and events, that are connected with it.
     *
     * @param appointmentDTO - data for saving
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(AppointmentDTO appointmentDTO) {
        List<LocalDate> dates = getDatesBetweenStartAndEnd(appointmentDTO.getStartDate(), appointmentDTO.getEndDate(),
                appointmentDTO.getDays());

        List<LocalDateTime> datesWithTime = setTimeToDates(dates, appointmentDTO.getTime());

        Appointment appointment = new Appointment();
        appointmentDTO.setStatus(AppointmentStatus.ACTIVE);
        AppointmentConverter.converterAppointmentDtoToAppointment(appointmentDTO, appointment);
        appointment.setTreatment(treatmentDAO.getByName(appointmentDTO.getTreatmentDtoName()));
        appointment.setPatient(mapper.map(appointmentDTO.getPatientDTO(), Patient.class));
        appointmentDAO.create(appointment);
        appointment.setEventList(createEventsOfAppointment(datesWithTime, appointment));
        appointmentDAO.update(appointment);
        messageSender.send(UPDATE);
        logger.info("Appointment is created.");
    }

    /**
     * Updates appointment, cancels current events with comments “by Doctor” and creats new events.
     *
     * @param appointmentDTO - data for updating
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(AppointmentDTO appointmentDTO) {
        Patient patient = patientDAO.getById(appointmentDTO.getPatientDtoId());
        List<Event> eventListOld = eventDAO.getPlannedEventsByAppointmentId(appointmentDTO.getId());
        changeEventsStatusToCancelledByDoctor(eventListOld);
        patientService.changePatientStatusToDischarge(patient);

        List<LocalDate> dates = getDatesBetweenStartAndEnd(appointmentDTO.getStartDate(), appointmentDTO.getEndDate(),
                appointmentDTO.getDays());
        List<LocalDateTime> datesWithTime = setTimeToDates(dates, appointmentDTO.getTime());
        Appointment appointment = appointmentDAO.getById(appointmentDTO.getId());
        AppointmentConverter.converterAppointmentDtoToAppointment(appointmentDTO, appointment);
        appointment.setTreatment(treatmentDAO.getByName(appointmentDTO.getTreatmentDtoName()));
        appointment.setEventList(createEventsOfAppointment(datesWithTime, appointment));
        appointmentDAO.update(appointment);
        messageSender.send(UPDATE);
        logger.info("Appointment is updated.");
    }

    /**
     * Changes appointment status from "ACTIVE" to "CANCELLED"
     *
     * @param id - id of editing appointment
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeStatusToCancelledById(Integer id) {
        List<Event> eventList = eventDAO.getPlannedEventsByAppointmentId(id);
        changeEventsStatusToCancelledByDoctor(eventList);
        Appointment appointment = appointmentDAO.getById(id);
        Patient patient = patientDAO.getById(appointment.getPatientId());
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentDAO.update(appointment);
        patientService.changePatientStatusToDischarge(patient);
        messageSender.send(UPDATE);
        logger.info("Appointment is deleted.");
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDTO getById(Integer id) {
        Appointment appointment = appointmentDAO.getById(id);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        AppointmentConverter.converterAppointmentToAppointmentDTO(appointment, appointmentDTO);
        appointmentDTO.setPatientDTO(mapper.map(appointment.getPatient(), PatientDTO.class));
        appointmentDTO.setTreatmentDTO(mapper.map(appointment.getTreatment(), TreatmentDTO.class));
        return appointmentDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentDTO> getTreatmentByLikeNames(String name) {
        List<Treatment> treatmentList = treatmentDAO.getByLikeName(name);
        return treatmentList.stream().map(treatment -> mapper.map(treatment, TreatmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TreatmentDTO> getAllTreatments() {
        List<Treatment> treatmentList = treatmentDAO.getAll();
        return treatmentList.stream().map(treatment -> mapper.map(treatment, TreatmentDTO.class))
                .collect(Collectors.toList());
    }

    public List<LocalDate> getDatesBetweenStartAndEnd(LocalDate startDate, LocalDate endDate, List<DaysOfWeek> days) {

        int daysInWeek = 7;

        List<LocalDate> daysInRange = new ArrayList<>();

        DayOfWeek dowOfStart = startDate.getDayOfWeek();

        for (DaysOfWeek day : days) {

            int difference = Optional.ofNullable(getDayOfWeek(day)).orElse(dowOfStart).getValue() - dowOfStart.getValue();
            if (difference < 0) {
                difference += daysInWeek;
            }

            LocalDate currentDay = startDate.plusDays(difference);
            while ((currentDay.isEqual(startDate) || currentDay.isAfter(startDate))
                    && (currentDay.isBefore(endDate) || currentDay.isEqual(endDate))) {
                daysInRange.add(currentDay);
                currentDay = currentDay.plusDays(daysInWeek);
            }
        }

        return daysInRange;
    }

    private DayOfWeek getDayOfWeek(DaysOfWeek daysOfWeek) {
        DayOfWeek day = null;
        switch (daysOfWeek) {
            case MON:
                day = DayOfWeek.MONDAY;
                break;
            case TUE:
                day = DayOfWeek.TUESDAY;
                break;
            case WED:
                day = DayOfWeek.WEDNESDAY;
                break;
            case THU:
                day = DayOfWeek.THURSDAY;
                break;
            case FRI:
                day = DayOfWeek.FRIDAY;
                break;
            case SAT:
                day = DayOfWeek.SATURDAY;
                break;
            case SUN:
                day = DayOfWeek.SUNDAY;
                break;
        }
        return day;
    }


    private List<LocalDateTime> setTimeToDates(List<LocalDate> localDate, List<TimeOfTheDay> timeOfTheDays) {
        List<LocalDateTime> dates = new ArrayList<>();
        for (int i = 0; i < timeOfTheDays.size(); i++) {
            String[] time = timeOfTheDays.get(i).toString().split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            for (int j = 0; j < localDate.size(); j++) {
                dates.add(localDate.get(j).atTime(hour, minute));
            }
        }
        return dates;
    }

    private List<Event> createEventsOfAppointment(List<LocalDateTime> datesWithTime, Appointment appointment) {
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < datesWithTime.size(); i++) {
            if(datesWithTime.get(i).isBefore(LocalDateTime.now())){
                continue;
            }
            Event event = new Event();
            event.setDate(datesWithTime.get(i));
            event.setStatus(EventStatus.PLANNED);
            event.setAppointment(appointment);
            eventDAO.create(event);
            eventList.add(event);
        }
        logger.info("Events of appointment are created.");
        return eventList;

    }

    private void changeEventsStatusToCancelledByDoctor(List<Event> eventList) {
        for (Event event : eventList) {
            event.setStatus(EventStatus.CANCELLED);
            event.setComment("by Doctor");
            eventDAO.update(event);
        }
        logger.info("Events are cancelled by doctor.");
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
}