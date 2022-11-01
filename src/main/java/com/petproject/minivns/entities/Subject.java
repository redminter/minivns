package com.petproject.minivns.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subjects")
@EqualsAndHashCode(of = {"id", "name"})
public class Subject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "subjects_start12", initialValue = 12)
    @Id
    @Column(name = "id", nullable = false)
    @NotBlank
    private int id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "user_id")
    private int userId;

    @Column(name = "vns_url")
    private String vnsUrl;

    @Column(name = "lection_url")
    private String lectionUrl;

    @Column(name = "prakt_url")
    private String praktUrl;

    @Column(name = "lab_url")
    private String labUrl;

    @NonNull
    @ManyToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> user_id;

//    @OneToMany(mappedBy = "task_id")
//    private List<Task> taskById;

}
