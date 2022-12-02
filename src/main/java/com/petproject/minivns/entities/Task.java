package com.petproject.minivns.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
@EqualsAndHashCode(of = {"id", "name"})
public class Task {
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_start8")
    @SequenceGenerator(name = "tasks_start8", allocationSize = 1)
    @Id
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "title")
    private String title;

//    @NonNull
//    @Column(name = "subject_id")
//    private int subjectId;

    @Column(name = "link")
    private String link;

    @Column(name = "deadline")
    private LocalDateTime deadline;

//    @NonNull
//    @Column(name = "state_id")
//    private int stateId;

    public Task(@NonNull String title, String link, LocalDateTime deadline) {
        this.title = title;
        this.link = link;
        this.deadline = deadline;
    }

    public Task(@NonNull Integer id, @NonNull String title, String link, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.deadline = deadline;
    }

    public Task(@NonNull String title, String link, LocalDateTime deadline, @NonNull Subject subjectBySubjectId, @NonNull State stateByStateId, @NonNull User user
    ) {
        this.title = title;
        this.link = link;
        this.deadline = deadline;
        this.subjectBySubjectId = subjectBySubjectId;
        this.stateByStateId = stateByStateId;
        this.user = user;
    }

//    @NonNull
//    @Column(name = "user_id")
//    private int userId;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subjectBySubjectId;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State stateByStateId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
