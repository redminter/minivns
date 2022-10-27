package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(of = {"id", "name"})
public class Roles {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "rolesByRoleId")
    private Collection<Users> usersById;
}
