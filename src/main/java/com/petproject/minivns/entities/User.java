package com.petproject.minivns.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"id", "firstName", "lastName"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_start7")
    @SequenceGenerator(name = "users_start7", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

//    @NonNull
//    @Column(name = "role_id")
//    private int roleId;

    @NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NonNull
    @Column(name = "password")
    private String password;

//    @ManyToMany(mappedBy = "usersByUserId")
//    private List<Subject> subjectById;

//    @OneToMany(mappedBy = "usersByUserId")
//    private List<Task> taskById;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role_Id;

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, @NonNull Role role_Id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role_Id = role_Id;
    }

    public User(@NonNull Integer id, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
