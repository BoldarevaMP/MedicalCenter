package unicorn.converter;

import unicorn.dto.EventDTO;
import unicorn.dto.EventRestDTO;
import unicorn.entity.Event;

import java.time.LocalDateTime;
import java.util.Optional;

public class EventConverter {
    public static void convertEventToEventDTO(Event event, EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setDate(event.getDate());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setComment(event.getComment());
    }

    public static void convertEventDtoToEventDtoRest(EventDTO eventDTO, EventRestDTO eventRestDTO){
        eventRestDTO.setId(eventDTO.getId());
        eventRestDTO.setName(eventDTO.getPatientDtoLastName() + " " + eventDTO.getPatientDtoFirstName());
        eventRestDTO.setTreatmentName(eventDTO.getTreatmentDtoName());
        eventRestDTO.setDosage(eventDTO.getAppointmentDtoDosage());
        eventRestDTO.setDate(eventDTO.getDate().getHour() + ":00");
        eventRestDTO.setDosageForm(eventDTO.getTreatmentDtoDosageForm()!=null?
                eventDTO.getTreatmentDtoDosageForm().toString() : "");
        eventRestDTO.setStatus(eventDTO.getStatus().toString());
        eventRestDTO.setComment(eventDTO.getComment());
    }
}
