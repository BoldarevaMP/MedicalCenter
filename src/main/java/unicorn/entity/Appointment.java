package unicorn.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;

//    @ElementCollection(targetClass=DaysOfTheWeek.class)
//    @CollectionTable(name="appointments_dayoftheweek", joinColumns = @JoinColumn(name = "appointments_id"))
//    @Enumerated(EnumType.STRING)
//    @Column (name = "dayoftheweek")
//    private Collection<DaysOfTheWeek> days;
//
//
//    @ElementCollection(targetClass=TimeOfTheDay.class)
//    @CollectionTable(name="appointments_timeoftheday", joinColumns = @JoinColumn(name = "appointments_id"))
//    @Enumerated (EnumType.STRING)
//    @Column (name = "timeoftheday")
//    private Collection<TimeOfTheDay> period;

    @Column(name = "dosage")
    private Float dosage;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @OneToMany(mappedBy = "appointment")
    private List<Event> eventList;
}

