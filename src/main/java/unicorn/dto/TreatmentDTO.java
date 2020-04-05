package unicorn.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import unicorn.entity.enums.TreatmentDosageForm;
import unicorn.entity.enums.TreatmentType;

@Data
@NoArgsConstructor
public class TreatmentDTO {
    private Integer id;

    @NotBlank
    private String name;
    private TreatmentType type;
    private TreatmentDosageForm dosageForm;

}
