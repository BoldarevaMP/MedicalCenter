package unicorn.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import unicorn.entity.enums.DaysOfWeek;
import unicorn.entity.enums.TimeOfTheDay;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
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

    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(targetClass = DaysOfWeek.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "appointments_daysofweek", joinColumns = @JoinColumn(name = "appointment_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "dayofweek")
    private List<DaysOfWeek> days;

    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(targetClass = TimeOfTheDay.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "appointments_timeoftheday", joinColumns = @JoinColumn(name = "appointment_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "time")
    private List<TimeOfTheDay> time;

    @Column(name = "dosage")
    private Integer dosage;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Event> eventList;
}

