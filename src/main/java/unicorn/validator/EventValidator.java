package unicorn.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import unicorn.dto.EventDTO;
import unicorn.entity.enums.EventStatus;

@Component
public class EventValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return EventDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        EventDTO eventDTO = (EventDTO) o;

        if (eventDTO.getStatus().equals(EventStatus.CANCELLED) && eventDTO.getComment().length() < 3) {
            errors.rejectValue("comment", "Check.event.comment");
        }
    }
}
