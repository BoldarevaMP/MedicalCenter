package unicorn.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import unicorn.dto.UserDTO;

@Component
public class UserValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        if (!userDTO.getIdentKey().equals("Doctor2020") && !userDTO.getIdentKey().equals("Nurse2020")) {
            errors.rejectValue("identKey", "Check.userForm.identKey");
        }
    }
}