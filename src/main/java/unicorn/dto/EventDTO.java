package unicorn.dto;



import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {
    private Integer id;
    private Date date;
    private String timeOfTheDay;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeOfTheDay() {
        return timeOfTheDay;
    }

    public void setTimeOfTheDay(String timeOfTheDay) {
        this.timeOfTheDay = timeOfTheDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
    return getId() + " " + getDate() + " " + getTimeOfTheDay() + " " + getStatus();
    }

    //    private PatientDTO patient;
//    private TreatmentDTO treatment;

}
