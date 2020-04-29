package unicorn.converter;

import unicorn.dto.EventDTO;
import unicorn.dto.EventRestDTO;
import unicorn.entity.Event;

public class EventConverter {
    public static void convertEventToEventDTO(Event event, EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setDate(event.getDate());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setComment(event.getComment());
    }

    public static void convertEventToEventRestDto(Event event, EventRestDTO eventRestDTO) {
        eventRestDTO.setId(event.getId());
        eventRestDTO.setPatientLastName(event.getPatientLastName() + " " + event.getPatientFirstName());
        eventRestDTO.setTreatmentName(event.getTreatmentName());
        eventRestDTO.setDosage(event.getDosage());
        eventRestDTO.setDate(event.getDate().getHour() + ":00");
        eventRestDTO.setDosageForm(event.getDosageForm() != null ? event.getDosageForm().toString() : "");
        eventRestDTO.setStatus(event.getStatus().toString());
        eventRestDTO.setComment(event.getComment());
    }
}
