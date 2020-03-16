package unicorn.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import unicorn.entity.enums.TreatmentDosageForm;
import unicorn.entity.enums.TreatmentType;

@Data
@NoArgsConstructor
public class TreatmentDTO {
    private Integer id;
    private String name;
    private TreatmentType type;
    private TreatmentDosageForm dosageForm;

    public TreatmentDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
