package unicorn.dao.api;

import unicorn.entity.Appointment;

import java.util.List;

public interface AppointmentDAO extends GenericDAO <Appointment>{
    List<Appointment> getByPatientId(Integer id);


}
