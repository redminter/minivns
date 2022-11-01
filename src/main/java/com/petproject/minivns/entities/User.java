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
@Table(name = "users")
@EqualsAndHashCode(of = {"id", "name"})
public class User {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "users_start7", initialValue = 7)
    @Id
    @Column(name = "id")
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
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

//    @ManyToMany(mappedBy = "usersByUserId")
//    private List<Subject> subjectById;
//
//    @OneToMany(mappedBy = "usersByUserId")
//    private List<Task> taskById;
//
    @NonNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role_Id;

}
