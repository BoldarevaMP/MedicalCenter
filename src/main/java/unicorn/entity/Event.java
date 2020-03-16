package unicorn.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TimeOfTheDay;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table (name = "events")
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "period")
    private String timeOfTheDay;

    @Enumerated (EnumType.STRING)
    @Column (name = "status")
    private EventStatus status;

    @Column (name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;
}
