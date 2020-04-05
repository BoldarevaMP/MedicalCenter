package unicorn.service.api;


import unicorn.dto.AppointmentDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.enums.DaysOfWeek;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    void create(AppointmentDTO appointmentDTO);

    AppointmentDTO getById(Integer id);

    void changeStatusToCancelledById(Integer id);

    void update(AppointmentDTO appointmentDTO);

    List<TreatmentDTO> getTreatmentByLikeNames(String name);

    List<LocalDate> getDatesBetweenStartAndEnd(LocalDate startDate, LocalDate endDate, List<DaysOfWeek> days);
}
