package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import unicorn.dto.TreatmentDTO;
import unicorn.service.impl.TreatmentServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/treatments")
public class TreatmentController {

    @Autowired
    private TreatmentServiceImpl treatmentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<TreatmentDTO> getAllTreatments(){
        return treatmentService.getAllTreatments();
    }
}
