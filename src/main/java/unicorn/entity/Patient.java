package unicorn.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.PatientStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "healthinsurance")
    private Long healthInsurance;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PatientStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column(name = "diagnosis")
    private String diagnosis;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}
