package unicorn.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import unicorn.dto.PatientDTO;

@Component
public class PatientValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return PatientDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        PatientDTO patientDTO = (PatientDTO) o;

        if (patientDTO.getHealthInsurance() != null && patientDTO.getHealthInsurance() <= 0) {
            errors.rejectValue("healthInsurance", "Check.patient.healthInsurance");
        }
    }
}
