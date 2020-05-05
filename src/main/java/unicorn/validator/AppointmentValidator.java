package unicorn.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import unicorn.dto.AppointmentDTO;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.enums.TreatmentType;
import unicorn.service.api.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppointmentValidator implements Validator {

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AppointmentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AppointmentDTO appointmentDTO = (AppointmentDTO) o;

        if (appointmentDTO.getTreatmentType().equals(TreatmentType.MEDICINE)
                && appointmentDTO.getDosage() == null) {
            errors.rejectValue("dosage", "Check.appointment.dosage");
        }

        if (appointmentDTO.getTreatmentType().equals(TreatmentType.MEDICINE)
                && appointmentDTO.getDosage() != null && appointmentDTO.getDosage() <= 0) {
            errors.rejectValue("dosage", "CheckAmount.appointment.dosage");
        }

        if (appointmentDTO.getStartDate() == null) {
            errors.rejectValue("startDate", "Check.appointment.startDate");
        }
        if (appointmentDTO.getEndDate() == null) {
            errors.rejectValue("endDate", "Check.appointment.endDate");
        }
        if (appointmentDTO.getStartDate() != null && appointmentDTO.getEndDate() != null
                && appointmentDTO.getStartDate().isAfter(appointmentDTO.getEndDate())) {
            errors.rejectValue("endDate", "Verify.appointment.endDate");
        }
        if (appointmentDTO.getStartDate() != null
                && appointmentDTO.getStartDate().isBefore(LocalDate.now())) {
            errors.rejectValue("startDate", "Verify.appointment.startDate");
        }

        if (appointmentDTO.getTreatmentDtoName() == null || appointmentDTO.getTreatmentDtoName().equals("")) {
            errors.rejectValue("treatmentDTO.name", "Check.appointment.treatmentDTO.name");
        } else {
            List<TreatmentDTO> treatmentDTOList = appointmentService.getAllTreatments();
            boolean isValid = false;
            for (TreatmentDTO treatmentDTO : treatmentDTOList) {
                if (appointmentDTO.getTreatmentDtoName().equals(treatmentDTO.getName())) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                errors.rejectValue("treatmentDTO.name", "Verify.appointment.treatmentDTO.name");
            }
        }

        if (appointmentDTO.getDays().isEmpty()) {
            errors.rejectValue("days", "Check.appointment.days");
        }

        if (appointmentDTO.getStartDate() != null && appointmentDTO.getEndDate() != null && !appointmentDTO.getDays().isEmpty()) {
            List<LocalDate> localDates = appointmentService.getDatesBetweenStartAndEnd(appointmentDTO.getStartDate(),
                    appointmentDTO.getEndDate(), appointmentDTO.getDays());
            if (localDates.isEmpty()) {
                errors.rejectValue("days", "CheckPattern.appointment.days");
            }
        }
        if (appointmentDTO.getTime().isEmpty()) {
            errors.rejectValue("time", "Check.appointment.time");
        }
    }
}
