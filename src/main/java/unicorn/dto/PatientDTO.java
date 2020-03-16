package unicorn.dto;


import unicorn.entity.enums.PatientStatus;

import java.util.List;

public class PatientDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer healthInsurance;
    private PatientStatus status;
    private UserDTO userDTO;
    private List<DiagnosisDTO> diagnosisList;

    // list appointments, list events
}
