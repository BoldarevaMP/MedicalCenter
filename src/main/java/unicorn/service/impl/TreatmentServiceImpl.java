package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicorn.dao.api.TreatmentDAO;
import unicorn.dao.impl.TreatmentDAOImpl;
import unicorn.entity.Treatment;
import unicorn.service.api.TreatmentService;

import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private static final Logger logger = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private TreatmentDAOImpl treatmentDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(TreatmentDAO treatmentDAO) {
            if (treatmentDAO != null) {
                Treatment treatment = modelMapper.map(treatmentDAO, Treatment.class);
                treatmentDAO.create(treatment);
            }
    }



   public List getAllTreatments(){
       return null;
    }
}
