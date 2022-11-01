package com.petproject.minivns.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
@EqualsAndHashCode(of = {"id", "name"})
public class Task {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "tasks_start8", initialValue = 8)
    @Id
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "link")
    private String link;

    @Column(name = "deadline")
    private LocalDate deadline;

    @NonNull
    @Column(name = "state_id")
    private int stateId;

//    @NonNull
//    @Column(name = "user_id")
//    private int userId;

//    @NonNull
//    @ManyToOne
//    @JoinColumn(name = "subject_id", referencedColumnName = "id")
//    private Subject subjectBySubjectId;

//    @NonNull
//    @ManyToOne
//    @JoinColumn(name = "state_id", referencedColumnName = "id")
//    private State stateByStateId;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;
}
