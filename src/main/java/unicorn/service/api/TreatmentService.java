package unicorn.service.api;

import unicorn.dto.TreatmentDTO;

import java.util.List;

public interface TreatmentService {
    void create(TreatmentDTO treatmentDTO);

    TreatmentDTO getByName (String name);
    List<TreatmentDTO> getByLikeNames(String name);
}
