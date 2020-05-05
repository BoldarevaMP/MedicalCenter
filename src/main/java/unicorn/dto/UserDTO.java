package unicorn.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import unicorn.entity.enums.Role;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User DTO class
 */

@Data
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    @NotNull
    @Size(min = 1, max = 16, message = "Field must be between 1 and 16 characters.")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 16, message = "Field must be between 1 and 16 characters.")
    private String lastName;

    @Email
    @NotNull
    @Size(min = 4, max = 32, message = "Field must be between 4 and 32 characters.")
    private String email;

    @NotNull
    @Size(min = 8, max = 32, message = "Field must be between 8 and 32 characters.")
    private String password;

    @NotNull
    @Size(min = 8, max = 32, message = "Field must be between 8 and 32 characters.")
    @Transient
    private String confirmPassword;

    @NotNull
    @Transient
    @Size(min = 9, max = 10, message = "Field must be 9 or 10 characters.")
    private String identKey;

    private Role role;
}