package unicorn.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TreatmentDosageForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EventStatus status;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Integer getPatientId() {
        return getAppointment().getPatient().getId();
    }

    public String getPatientLastName() {return getAppointment().getPatient().getLastName();}

    public String getPatientFirstName() {return getAppointment().getPatient().getFirstName();}

    public String getTreatmentName() {return getAppointment().getTreatment().getName();}

    public Integer getDosage() {return getAppointment().getDosage();}

    public TreatmentDosageForm getDosageForm() {return getAppointment().getTreatment().getDosageForm();}
}
