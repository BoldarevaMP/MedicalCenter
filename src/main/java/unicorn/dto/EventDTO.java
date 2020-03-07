package unicorn.dto;



import lombok.Data;

import java.util.Date;


public class EventDTO {
    private Integer id;
    private Date date;
    private String timeOfTheDay;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeOfTheDay(String timeOfTheDay) {
        this.timeOfTheDay = timeOfTheDay;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getTimeOfTheDay() {
        return timeOfTheDay;
    }

    public String getStatus() {
        return status;
    }

    private String status;

    //    private PatientDTO patient;
//    private TreatmentDTO treatment;

}
