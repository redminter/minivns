package com.petproject.minivns.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "states")
@EqualsAndHashCode(of = {"id", "name"})
public class State {
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "states_start7")
    @SequenceGenerator(name = "states_start7", allocationSize = 1)
    @Id
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "statesByStateId")
//    private List<Task> taskById;

    public State (String name){
        this.name = name;
    }
}
