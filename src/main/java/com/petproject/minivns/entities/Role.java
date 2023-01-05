package com.petproject.minivns.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(of = {"id", "name"})
public class Role implements GrantedAuthority {
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_start3")
    @SequenceGenerator(name = "roles_start3", allocationSize = 1)
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

    @Override
    public String getAuthority() {
        return name;
    }
}
