package unicorn.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TreatmentDosageForm;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class EventDTO {

    private Integer id;
    private LocalDateTime date;
    private EventStatus status;
    private String comment;
    @JsonIgnore
    private AppointmentDTO appointmentDTO;

    public String getPatientDtoLastName() {
        return getAppointmentDTO().getPatientDTO().getLastName();
    }

    public String getPatientDtoFirstName() {
        return getAppointmentDTO().getPatientDTO().getFirstName();
    }

    public String getTreatmentDtoName() {
        return getAppointmentDTO().getTreatmentDtoName();
    }

    public Integer getAppointmentDtoDosage() {
        return getAppointmentDTO().getDosage();
    }

    public TreatmentDosageForm getTreatmentDtoDosageForm(){
        return getAppointmentDTO().getTreatmentDTO().getDosageForm();
    }

}


