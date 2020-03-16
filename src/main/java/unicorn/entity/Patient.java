package unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicorn.entity.enums.PatientStatus;



import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table (name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Size(min = 16, max = 16, message = "This field must contain 16 digits.")
    @Column(name ="healthinsurance")
    private Integer healthInsurance;

    @Enumerated (EnumType.STRING)
    @Column (name = "status")
    private PatientStatus status;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patients_diagnoses", joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
    private List<Diagnosis> diagnosisList;

    @OneToMany (mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private List<Event> events;
}
