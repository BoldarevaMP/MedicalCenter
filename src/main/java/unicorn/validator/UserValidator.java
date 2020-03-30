package unicorn.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import unicorn.dto.UserDTO;
import unicorn.service.api.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(userDTO.getEmail())) {
            errors.rejectValue("email", "Invalid.userForm.email");
        }

        if (userDTO.getPassword().length() < 8 || userDTO.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        if (!userDTO.getIdentKey().equals("Doctor2020") && !userDTO.getIdentKey().equals("Nurse2020")){
            errors.rejectValue("identKey","Check.userForm.identKey");
        }
    }
}