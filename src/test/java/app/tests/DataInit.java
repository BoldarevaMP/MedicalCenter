package app.tests;

import unicorn.dto.*;
import unicorn.entity.*;
import unicorn.entity.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class DataInit {

    static EventDTO eventDTO;
    static PatientDTO patientDTO;
    static TreatmentDTO treatmentDTO;
    static AppointmentDTO appointmentDTO;
    static UserDTO userDTO;

    static Event event;
    static Patient patient;
    static Treatment treatment;
    static Appointment appointment;
    static User user;

    static EventRestDTO eventRestDTO;

    public static void setUp(){
        eventDTO = new EventDTO();
        appointmentDTO = new AppointmentDTO();
        patientDTO = new PatientDTO();
        treatmentDTO = new TreatmentDTO();
        userDTO = new UserDTO();
        eventRestDTO = new EventRestDTO();

        treatmentDTO.setId(1000);
        treatmentDTO.setName("codeine");
        treatmentDTO.setDosageForm(TreatmentDosageForm.TAB);

        userDTO.setId(1000);
        userDTO.setLastName("TestDoctorLastName");
        userDTO.setFirstName("TestDoctorFirstName");
        userDTO.setRole(Role.ROLE_DOCTOR);
        userDTO.setEmail("testdoctor@mail.ru");
        userDTO.setIdentKey("Doctor2020");
        userDTO.setPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
        userDTO.setConfirmPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");

        patientDTO.setId(1000);
        patientDTO.setLastName("White");
        patientDTO.setFirstName("Maria");
        patientDTO.setStatus(PatientStatus.TREATED);
        patientDTO.setDoctorDTO(userDTO);
        patientDTO.setDiagnosis("flu");
        patientDTO.setHealthInsurance(123321L);
        patientDTO.setStartDate(LocalDate.of(2020,4,26));

        appointmentDTO.setId(1000);
        appointmentDTO.setStatus(AppointmentStatus.ACTIVE);
        appointmentDTO.setStartDate(LocalDate.of(2020,4,20));
        appointmentDTO.setEndDate(LocalDate.of(2020,4,30));
        appointmentDTO.setTreatmentDTO(treatmentDTO);
        appointmentDTO.setDosage(1);
        appointmentDTO.setPatientDTO(patientDTO);
        appointmentDTO.setDays(Arrays.asList(DaysOfWeek.MON, DaysOfWeek.TUE, DaysOfWeek.WED, DaysOfWeek.THU,
                DaysOfWeek.FRI, DaysOfWeek.SAT, DaysOfWeek.SUN));
        appointmentDTO.setTime(Arrays.asList(TimeOfTheDay.AM_8, TimeOfTheDay.PM_2));

        eventDTO.setId(1000);
        eventDTO.setStatus(EventStatus.DONE);
        eventDTO.setAppointmentDTO(appointmentDTO);
        eventDTO.setDate(LocalDateTime.of(2020,4,28,8,0));


        event = new Event();
        appointment = new Appointment();
        patient = new Patient();
        treatment = new Treatment();
        user = new User();

        treatment.setId(1000);
        treatment.setName("codeine");
        treatment.setDosageForm(TreatmentDosageForm.TAB);


        user.setId(1000);
        user.setLastName("TestDoctorLastName");
        user.setFirstName("TestDoctorFirstName");
        user.setRole(Role.ROLE_DOCTOR);
        user.setEmail("testdoctor@mail.ru");
        user.setPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");

        patient.setId(1000);
        patient.setLastName("White");
        patient.setFirstName("Maria");
        patient.setStatus(PatientStatus.TREATED);
        patient.setDoctor(user);
        patient.setDiagnosis("flu");
        patient.setHealthInsurance(123321L);
        patient.setStartDate(LocalDate.of(2020,4,26));

        appointment.setId(1000);
        appointment.setStatus(AppointmentStatus.ACTIVE);
        appointment.setStartDate(LocalDate.of(2020,4,20));
        appointment.setEndDate(LocalDate.of(2020,4,30));
        appointment.setTreatment(treatment);
        appointment.setDosage(1);
        appointment.setPatient(patient);
        appointment.setDays(Arrays.asList(DaysOfWeek.MON, DaysOfWeek.TUE, DaysOfWeek.WED, DaysOfWeek.THU,
                DaysOfWeek.FRI, DaysOfWeek.SAT, DaysOfWeek.SUN));
        appointment.setTime(Arrays.asList(TimeOfTheDay.AM_8, TimeOfTheDay.PM_2));

        event.setId(1000);
        event.setStatus(EventStatus.DONE);
        event.setAppointment(appointment);
        event.setDate(LocalDateTime.of(2020,4,28,8,0));

        eventRestDTO.setId(1000);
        eventRestDTO.setPatientLastName("TestPatientName");
        eventRestDTO.setTreatmentName("codeine");
        eventRestDTO.setDosage(1);
        eventRestDTO.setDosageForm("TAB");
        eventRestDTO.setDate("8:00");
        eventRestDTO.setStatus("DONE");

    }


}
