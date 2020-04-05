package unicorn.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import unicorn.service.impl.EventServiceImpl;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(EventServiceImpl.class);

    @ExceptionHandler({UnicornValidationException.class})
    public ModelAndView handleRuntimeException(UnicornValidationException ex) {
        logger.error("Exception!" + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/exceptionPage");
        modelAndView.addObject("ex", ex);
        return modelAndView;
    }
}