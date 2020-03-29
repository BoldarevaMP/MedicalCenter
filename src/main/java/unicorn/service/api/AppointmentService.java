package unicorn.service.api;


import unicorn.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    void create(AppointmentDTO appointmentDTO);

    AppointmentDTO getById(Integer id);

    List<AppointmentDTO> getByPatientId (Integer id);

    void deleteById (Integer id);
}
