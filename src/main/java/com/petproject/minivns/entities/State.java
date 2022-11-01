package com.petproject.minivns.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "states")
@EqualsAndHashCode(of = {"id", "name"})
public class State {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "states_start7", initialValue = 7)
    @Id
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "statesByStateId")
//    private List<Task> taskById;

}
