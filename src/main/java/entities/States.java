package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "states")
@EqualsAndHashCode(of = {"id", "name"})
public class States {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "statesByStateId")
    private List<Tasks> tasksById;

}
