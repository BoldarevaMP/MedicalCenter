package unicorn.entity;

import unicorn.entity.enums.EventStatus;
import unicorn.entity.enums.TimeOfTheDay;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table (name = "event")
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
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
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    public Event() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeOfTheDay(TimeOfTheDay timeOfTheDay) {
        this.timeOfTheDay = timeOfTheDay;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public TimeOfTheDay getTimeOfTheDay() {
        return timeOfTheDay;
    }

    public EventStatus getStatus() {
        return status;
    }

    public Patient getPatient() {
        return patient;
    }

    public Treatment getTreatment() {
        return treatment;
    }
}
