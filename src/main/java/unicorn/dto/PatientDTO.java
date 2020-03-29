package unicorn.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.PatientStatus;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.BitSet;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PatientDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull
    private Long healthInsurance;
    private LocalDate startDate;
    private PatientStatus status;
    private UserDTO doctorDTO;
    private String diagnosis;

}
