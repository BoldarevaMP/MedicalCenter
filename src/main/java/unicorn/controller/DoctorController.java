package unicorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import unicorn.dao.DoctorDao;
import unicorn.entity.Doctor;

import java.util.List;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    private DoctorDao doctorDao;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Doctor saveDoctor(){
        Doctor doctor = new Doctor();
        doctor.setFirstName("111");
        doctor.setLastName("222");
        doctorDao.create(doctor);
        return doctor;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Doctor getEvent(){
        List<Doctor> events=doctorDao.getAll();
        return doctorDao.getById(events.get(0).getId());
    }
}
