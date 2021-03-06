package unicorn.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import unicorn.entity.enums.PatientStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Patient DTO class
 */

@Getter
@Setter
@NoArgsConstructor
public class PatientDTO implements Serializable {

    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Long healthInsurance;
    private LocalDate startDate;
    private PatientStatus status;
    private UserDTO doctorDTO;
    @NotEmpty
    private String diagnosis;

}
