package unicorn.service.api;


import unicorn.dto.AppointmentDTO;

public interface AppointmentService {
    void create(AppointmentDTO appointmentDTO);

    AppointmentDTO getById(Integer id);
}
