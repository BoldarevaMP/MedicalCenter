package unicorn.converter;

import unicorn.dto.AppointmentDTO;
import unicorn.entity.Appointment;

public class AppointmentCorverter {
    public static void converterAppointmentDtoToAppointment(AppointmentDTO appointmentDTO, Appointment appointment) {
        appointment.setId(appointmentDTO.getId());
        appointment.setStartDate(appointmentDTO.getStartDate());
        appointment.setEndDate(appointmentDTO.getEndDate());
        appointment.setDosage(appointmentDTO.getDosage());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setDays(appointmentDTO.getDays());
        appointment.setTime(appointmentDTO.getTime());
    }

    public static void converterAppointmentToAppointmentDTO(Appointment appointment, AppointmentDTO appointmentDTO) {
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDosage(appointment.getDosage());
        appointmentDTO.setStatus(appointment.getStatus());
        appointmentDTO.setStartDate(appointment.getStartDate());
        appointmentDTO.setEndDate(appointment.getEndDate());
        appointmentDTO.setDays(appointment.getDays());
        appointmentDTO.setTime(appointment.getTime());
    }
}
