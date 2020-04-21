package unicorn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventRestDTO implements Serializable {
    private Integer id;

    private String name;

    private String treatmentName;

    private Integer dosage;

    private String dosageForm;

    private LocalDateTime date;

    private String status;

    private String comment;
}
