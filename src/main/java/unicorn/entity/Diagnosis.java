package unicorn.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table (name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "name")
    private String name;

    @ManyToMany (mappedBy = "diagnosisList")
    private List<Patient> patientList;
}
