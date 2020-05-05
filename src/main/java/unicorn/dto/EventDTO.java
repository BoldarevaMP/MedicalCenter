package unicorn.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.EventStatus;

import java.time.LocalDateTime;

/**
 * Event DTO class
 */

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Integer id;
    private LocalDateTime date;
    private EventStatus status;
    private String comment;
    private AppointmentDTO appointmentDTO;
}

