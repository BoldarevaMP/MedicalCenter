package unicorn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import unicorn.entity.enums.AppointmentStatus;
import unicorn.entity.enums.DaysOfWeek;
import unicorn.entity.enums.TimeOfTheDay;
import unicorn.entity.enums.TreatmentType;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
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

    private AppointmentStatus status;

    private PatientDTO patientDTO;

    private TreatmentDTO treatmentDTO;


    public String getTreatmentDtoName() {
        return getTreatmentDTO().getName();
    }

    public Integer getPatientDtoId() {
        return getPatientDTO().getId();
    }

    public TreatmentType getTreatmentType() {
        return getTreatmentDTO().getType();
    }
}
