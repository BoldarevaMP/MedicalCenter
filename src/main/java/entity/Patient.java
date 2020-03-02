package entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@Table (name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Size(min = 16, max = 16, message = "This field must contain 16 digits.")
    @Column(name ="healthinsurance")
    private Integer healthInsurance;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;



    //    private diagnosis
//    private String AttendingPhisician;

//    private enum status;
}
