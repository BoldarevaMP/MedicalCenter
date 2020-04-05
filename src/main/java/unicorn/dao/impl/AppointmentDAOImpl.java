package unicorn.dao.impl;

import org.springframework.stereotype.Repository;
import unicorn.dao.api.AppointmentDAO;
import unicorn.entity.Appointment;

import java.util.List;

@Repository
public class AppointmentDAOImpl extends GenericDAOImpl<Appointment> implements AppointmentDAO {

    @Override
    public List<Appointment> getByPatientId(Integer patId) {
        return (List<Appointment>) entityManager.createNativeQuery("SELECT * FROM appointments " +
                "WHERE patient_id = :patId AND status LIKE 'ACTIVE'", Appointment.class)
                .setParameter("patId", patId).getResultList();
    }
}
