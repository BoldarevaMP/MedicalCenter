package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.impl.TreatmentDAOImpl;
import unicorn.dto.TreatmentDTO;
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
    @Transactional (propagation = Propagation.REQUIRED)
    public void create(TreatmentDTO treatmentDTO) {
            if (treatmentDTO != null) {
                Treatment treatment = modelMapper.map(treatmentDAO, Treatment.class);
                treatmentDAO.create(treatment);
            }
    }

}
