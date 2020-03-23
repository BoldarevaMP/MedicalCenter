package unicorn.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicorn.entity.Patient;
import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TreatmentDosageForm;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class EventDTO {

    private Integer id;
    private LocalDateTime date;
    private EventStatus status;
    private String comment;
    @JsonIgnore
    private AppointmentDTO appointmentDTO;


}


