package unicorn.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicorn.dao.TreatmentDAO;
import unicorn.dao.TreatmentDAOImpl;
import unicorn.entity.Treatment;

import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService{

    @Autowired
    private TreatmentDAOImpl treatmentDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(TreatmentDAO treatmentDAO) {
        //public AddressDTO create(AddressDTO addressDTO) {
            if (treatmentDAO != null) {
                Treatment treatment = modelMapper.map(treatmentDAO, Treatment.class);
                treatmentDAO.create(treatment);
            }
    }



   public List getAllTreatments(){
       return treatmentDAO.getAllTreatments();
    }
}
