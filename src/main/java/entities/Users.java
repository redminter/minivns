package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"id", "name"})
public class Users {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "role_id")
    private int roleId;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "usersByUserId")
    private Collection<Subjects> subjectsById;

    @OneToMany(mappedBy = "usersByUserId")
    private Collection<Tasks> tasksById;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles rolesByRoleId;

}
