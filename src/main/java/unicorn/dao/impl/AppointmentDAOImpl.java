package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.AppointmentDAO;
import unicorn.entity.Appointment;
import unicorn.entity.Event;

import java.util.List;

@Repository
public class AppointmentDAOImpl extends GenericDAOImpl <Appointment> implements AppointmentDAO {

    @Override
    public List<Appointment> getByPatientId(Integer patId) {
        List<Appointment> list = entityManager.createNativeQuery("SELECT * FROM appointments WHERE patient_id = :patId", Appointment.class).setParameter("patId", patId).getResultList();
        return list;
    }
}
