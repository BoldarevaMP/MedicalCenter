package unicorn.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.EventStatus;

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


}


