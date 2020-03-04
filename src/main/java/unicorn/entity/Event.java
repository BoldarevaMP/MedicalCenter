package unicorn.entity;

import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TimeOfTheDay;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@Table (name = "event")
public class Event {

    @Id
    @Column (name = "id")
    private Integer id;

    @Column (name = "date")
    private Date date;

    @Column (name = "period")
    private TimeOfTheDay timeOfTheDay;

    @Enumerated (EnumType.STRING)
    @Column (name = "status")
    private EventStatus status;

    @ManyToOne
    @Column (name = "patient_id")
    private Patient patient;

    @ManyToOne
    @Column (name = "treatment_id")
    private Treatment treatment;

}
