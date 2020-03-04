package unicorn.entity;

import unicorn.entity.enums.DaysOfTheWeek;
import unicorn.entity.enums.TimeOfTheDay;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "startdate")
    private Date startDate;

    @Column (name = "enddate")
    private Date endDate;

    @Enumerated (EnumType.STRING)
    @Column (name = "dayoftheweek")
    private List<DaysOfTheWeek> days;

    //timeoftheday
    @Enumerated (EnumType.STRING)
    @Column (name = "timeoftheday")
    private List<TimeOfTheDay> period;

    @Column (name = "dosage")
    private Float dosage;

    @ManyToOne
    @JoinColumn (name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn (name = "treatment_id")
    private Treatment treatment;
}
