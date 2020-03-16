package unicorn.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class EventDTO {
    private Integer id;
    private LocalDate date;
    private String timeOfTheDay;
    private PatientDTO patientDTO;
    private TreatmentDTO treatmentDTO;
    private String status;
    private String comment;


}
