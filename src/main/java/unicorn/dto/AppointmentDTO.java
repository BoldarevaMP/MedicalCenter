package unicorn.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import unicorn.entity.enums.DaysOfTheWeek;
import unicorn.entity.enums.TimeOfTheDay;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<DaysOfTheWeek> days;
    private List<TimeOfTheDay> time;
    private Float dosage;
    private PatientDTO patientDTO;
    private TreatmentDTO treatmentDTO;

}
