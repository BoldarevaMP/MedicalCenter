package entity;

import entity.enums.TreatmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table (name = "treatment")
public class Treatment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column (name = "name")
    private String name;

    @Enumerated (EnumType.STRING)
    @Column (name = "type")
    private TreatmentType type;

}
