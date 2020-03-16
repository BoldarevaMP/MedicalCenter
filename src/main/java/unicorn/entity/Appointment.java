package unicorn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import unicorn.entity.enums.DaysOfTheWeek;
import unicorn.entity.enums.TimeOfTheDay;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table (name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "startdate")
    private Date startDate;

    @Column (name = "enddate")
    private Date endDate;

    @ElementCollection(targetClass=DaysOfTheWeek.class)
    @CollectionTable(name="appointments_dayoftheweek", joinColumns = @JoinColumn(name = "appointments_id"))
    @Enumerated(EnumType.STRING)
    @Column (name = "dayoftheweek")
    private Collection<DaysOfTheWeek> days;

    //timeoftheday
    @ElementCollection(targetClass=TimeOfTheDay.class)
    @CollectionTable(name="appointments_timeoftheday", joinColumns = @JoinColumn(name = "appointments_id"))
    @Enumerated (EnumType.STRING)
    @Column (name = "timeoftheday")
    private Collection<TimeOfTheDay> period;

    @Column (name = "dosage")
    private Float dosage;

    @ManyToOne
    @JoinColumn (name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn (name = "treatment_id")
    private Treatment treatment;
}
