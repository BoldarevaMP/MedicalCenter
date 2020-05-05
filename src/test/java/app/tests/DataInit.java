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
    static UserDTO userDTODoctor;
    static UserDTO userDTONurse;

    static Event event;
    static Patient patient;
    static Treatment treatment;
    static Appointment appointment;
    static User userDoctor;
    static User userNurse;

    static EventRestDTO eventRestDTO;

    public static void setUpUserDTODoctor() {
        userDTODoctor = new UserDTO();
        userDTODoctor.setId(1000);
        userDTODoctor.setLastName("TestDoctorLastName");
        userDTODoctor.setFirstName("TestDoctorFirstName");
        userDTODoctor.setEmail("testdoctor@mail.ru");
        userDTODoctor.setIdentKey("Doctor2020");
        userDTODoctor.setPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
        userDTODoctor.setConfirmPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
    }

    public static void setUpUserDTONurse() {
        userDTONurse = new UserDTO();
        userDTONurse.setId(1001);
        userDTONurse.setLastName("TestNurseLastName");
        userDTONurse.setFirstName("TestNurseFirstName");
        userDTONurse.setEmail("testNurse@mail.ru");
        userDTONurse.setIdentKey("Nurse2020");
        userDTONurse.setPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
        userDTONurse.setConfirmPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
    }

    public static void setUpUserDoctor() {
        userDoctor = new User();
        userDoctor.setId(1000);
        userDoctor.setLastName("TestDoctorLastName");
        userDoctor.setFirstName("TestDoctorFirstName");
        userDoctor.setRole(Role.ROLE_DOCTOR);
        userDoctor.setEmail("testdoctor@mail.ru");
        userDoctor.setPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
    }

    public static void setUpUserNurse() {
        userNurse = new User();
        userNurse.setId(1001);
        userNurse.setLastName("TestNurseLastName");
        userNurse.setFirstName("TestNurseFirstName");
        userNurse.setRole(Role.ROLE_NURSE);
        userNurse.setEmail("testNurse@mail.ru");
        userNurse.setPassword("$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6");
    }

    public static void setUpPatientDTO() {
        patientDTO = new PatientDTO();
        patientDTO.setId(1000);
        patientDTO.setLastName("White");
        patientDTO.setFirstName("Maria");
        patientDTO.setStatus(PatientStatus.TREATED);
        patientDTO.setDoctorDTO(userDTODoctor);
        patientDTO.setDiagnosis("flu");
        patientDTO.setHealthInsurance(123321L);
        patientDTO.setStartDate(LocalDate.of(2020, 4, 26));
    }

    public static void setUpPatient() {
        patient = new Patient();
        patient.setId(1000);
        patient.setLastName("White");
        patient.setFirstName("Maria");
        patient.setStatus(PatientStatus.TREATED);
        patient.setDoctor(userDoctor);
        patient.setDiagnosis("flu");
        patient.setHealthInsurance(123321L);
        patient.setStartDate(LocalDate.of(2020, 4, 26));
    }

    public static void setUpTreatmentDTO() {
        treatmentDTO = new TreatmentDTO();
        treatmentDTO.setId(1000);
        treatmentDTO.setName("codeine");
        treatmentDTO.setDosageForm(TreatmentDosageForm.TAB);
    }

    public static void setUpTreatment() {
        treatment = new Treatment();
        treatment.setId(1000);
        treatment.setName("codeine");
        treatment.setDosageForm(TreatmentDosageForm.TAB);
    }

    public static void setUpAppointmentDTO() {
        appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(1000);
        appointmentDTO.setStartDate(LocalDate.of(2020, 4, 20));
        appointmentDTO.setEndDate(LocalDate.of(2020, 4, 30));
        appointmentDTO.setTreatmentDTO(treatmentDTO);
        appointmentDTO.setDosage(1);
        appointmentDTO.setPatientDTO(patientDTO);
        appointmentDTO.setDays(Arrays.asList(DaysOfWeek.MON, DaysOfWeek.TUE, DaysOfWeek.WED, DaysOfWeek.THU,
                DaysOfWeek.FRI, DaysOfWeek.SAT, DaysOfWeek.SUN));
        appointmentDTO.setTime(Arrays.asList(TimeOfTheDay.AM_8, TimeOfTheDay.PM_2));
    }

    public static void setUpAppointment() {
        appointment = new Appointment();
        appointment.setId(1000);
        appointment.setStatus(AppointmentStatus.ACTIVE);
        appointment.setStartDate(LocalDate.of(2020, 4, 20));
        appointment.setEndDate(LocalDate.of(2020, 4, 30));
        appointment.setTreatment(treatment);
        appointment.setDosage(1);
        appointment.setPatient(patient);
        appointment.setDays(Arrays.asList(DaysOfWeek.MON, DaysOfWeek.TUE, DaysOfWeek.WED, DaysOfWeek.THU,
                DaysOfWeek.FRI, DaysOfWeek.SAT, DaysOfWeek.SUN));
        appointment.setTime(Arrays.asList(TimeOfTheDay.AM_8, TimeOfTheDay.PM_2));
    }

    public static void setUpEventDTO() {
        eventDTO = new EventDTO();
        eventDTO.setId(1000);
        eventDTO.setStatus(EventStatus.DONE);
        eventDTO.setAppointmentDTO(appointmentDTO);
        eventDTO.setDate(LocalDateTime.of(2020, 4, 28, 8, 0));

    }

    public static void setUpEvent() {
        event = new Event();
        event.setId(1000);
        event.setStatus(EventStatus.DONE);
        event.setAppointment(appointment);
        event.setDate(LocalDateTime.of(2020, 4, 28, 8, 0));
    }

    public static void setUpEventRestDTO() {
        eventRestDTO = new EventRestDTO();
        eventRestDTO.setId(1000);
        eventRestDTO.setPatientLastName("TestPatientName");
        eventRestDTO.setTreatmentName("codeine");
        eventRestDTO.setDosage(1);
        eventRestDTO.setDosageForm("TAB");
        eventRestDTO.setDate("8:00");
        eventRestDTO.setStatus("DONE");
    }
}
