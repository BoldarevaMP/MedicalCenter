package unicorn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import unicorn.entity.enums.DaysOfWeek;
import unicorn.entity.enums.TimeOfTheDay;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO {
    private Integer id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;

    private List<DaysOfWeek> days;

    private List<TimeOfTheDay> time;

    private Integer dosage;

    private PatientDTO patientDTO;

    private TreatmentDTO treatmentDTO;

}
