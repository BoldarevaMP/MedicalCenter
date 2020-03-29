package unicorn.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.AppointmentDAO;
import unicorn.dao.api.EventDAO;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.PatientDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Appointment;
import unicorn.entity.enums.DaysOfWeek;
import unicorn.entity.enums.TimeOfTheDay;
import unicorn.service.api.AppointmentService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private EventDAO eventDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(AppointmentDTO appointmentDTO) {
        List<LocalDate> dates = getDatesBetweenStartAndEnd(appointmentDTO.getStartDate(), appointmentDTO.getEndDate(), appointmentDTO.getDays());
        List<LocalDateTime> dateWithTime = setTimeToDates(dates,appointmentDTO.getTime());

        appointmentDAO.create(mapper.map(appointmentDTO, Appointment.class));
    }

    private List<LocalDateTime> setTimeToDates(List<LocalDate> localDate, List<TimeOfTheDay> timeOfTheDays) {
        List<LocalDateTime> dates = new ArrayList<>();
        for (int i = 0; i < timeOfTheDays.size(); i++) {
            String[] time = timeOfTheDays.get(i).toString().split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
             for (int j = 0; j < localDate.size(); j++) {
                dates.add(localDate.get(j).atTime(hour,minute));
            }
        }
        return dates;
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

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> getByPatientId(Integer id) {
        List<Appointment> appointmentList = appointmentDAO.getByPatientId(id);
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for (int i = 0; i < appointmentList.size(); i++) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setId(appointmentList.get(i).getId());
            appointmentDTO.setStartDate(appointmentList.get(i).getStartDate());
            appointmentDTO.setEndDate(appointmentList.get(i).getEndDate());
            appointmentDTO.setDosage(appointmentList.get(i).getDosage());
            appointmentDTO.setDays(appointmentList.get(i).getDays());
            appointmentDTO.setTime(appointmentList.get(i).getTime());
            appointmentDTO.setPatientDTO(mapper.map(appointmentList.get(i).getPatient(), PatientDTO.class));
            appointmentDTO.setTreatmentDTO(mapper.map(appointmentList.get(i).getTreatment(), TreatmentDTO.class));
            appointmentDTOList.add(appointmentDTO);
        }

        return appointmentDTOList;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        appointmentDAO.delete(appointmentDAO.getById(id));
    }

    private List<LocalDate> getDatesBetweenStartAndEnd(LocalDate startDate, LocalDate endDate, List<DaysOfWeek> days) {

        List<LocalDate> daysInRange = new ArrayList<LocalDate>();

        DayOfWeek dowOfStart = startDate.getDayOfWeek();
        for (DaysOfWeek day : days) {

            int difference = getDayOfWeek(day).getValue() - dowOfStart.getValue();
            if (difference < 0) difference += 7;

            LocalDate currentDay = startDate.plusDays(difference);
            do {
                daysInRange.add(currentDay);
                currentDay = currentDay.plusDays(7);
            } while (currentDay.isBefore(endDate) || currentDay.isEqual(endDate));
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
}

