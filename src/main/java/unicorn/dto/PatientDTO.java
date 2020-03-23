package unicorn.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import unicorn.entity.enums.PatientStatus;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;
@Data
@NoArgsConstructor
public class PatientDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull
    private BigInteger healthInsurance;
    private PatientStatus status;
    private UserDTO doctorDTO;
    private String diagnosis;

}
