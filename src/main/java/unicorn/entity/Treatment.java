package unicorn.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unicorn.entity.enums.TreatmentDosageForm;
import unicorn.entity.enums.TreatmentType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TreatmentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "dosageform")
    private TreatmentDosageForm dosageForm;

    @OneToOne(mappedBy = "treatment")
    private Appointment appointment;

}
