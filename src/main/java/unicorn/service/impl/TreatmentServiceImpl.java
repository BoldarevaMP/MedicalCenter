package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.impl.TreatmentDAOImpl;
import unicorn.dto.TreatmentDTO;
import unicorn.entity.Treatment;
import unicorn.service.api.TreatmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private static final Logger logger = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private TreatmentDAOImpl treatmentDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional (readOnly = true)
    public TreatmentDTO getByName(String name) {
        Treatment treatment = treatmentDAO.getByName(name);
        return modelMapper.map(treatment, TreatmentDTO.class);
    }

    @Override
    @Transactional (readOnly = true)
    public List<TreatmentDTO> getByLikeNames(String name) {
        List<Treatment> treatmentList = treatmentDAO.getByLikeName(name);
        return treatmentList.stream().map(treatment -> modelMapper.map(treatment, TreatmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED)
    public void create(TreatmentDTO treatmentDTO) {
            if (treatmentDTO != null) {
                Treatment treatment = modelMapper.map(treatmentDAO, Treatment.class);
                treatmentDAO.create(treatment);
            }




    }


}
