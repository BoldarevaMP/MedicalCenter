package unicorn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Event Rest DTO class
 */

@Getter
@Setter
@NoArgsConstructor
public class EventRestDTO implements Serializable {
    private Integer id;

    private String patientLastName;

    private String treatmentName;

    private Integer dosage;

    private String dosageForm;

    private String date;

    private String status;

    private String comment;
}
