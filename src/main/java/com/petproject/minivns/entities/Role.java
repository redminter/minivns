package com.petproject.minivns.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(of = {"id", "name"})
public class Role {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "roles_start3", initialValue = 3)
    @Id
    @Column(name = "id")
    private Integer id;
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;
//    @OneToMany(mappedBy = "rolesByRoleId")
//    private List<User> userById;

    public Role(String name){
        this.name = name;
    }
    public Role(Integer id,String name){
        this.id = id;
        this.name = name;
    }
}
