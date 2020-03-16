package unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicorn.entity.enums.Role;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "firstname")
    private String firstName;

    @Column (name = "lastname")
    private String lastName;

    @Enumerated (EnumType.STRING)
    @Column (name = "role")
    private Role role;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Transient
    private String confirmPassword;

}

