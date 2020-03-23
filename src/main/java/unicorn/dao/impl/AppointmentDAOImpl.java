package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.AppointmentDAO;
import unicorn.entity.Appointment;

@Repository
public class AppointmentDAOImpl extends GenericDAOImpl <Appointment> implements AppointmentDAO {

}
