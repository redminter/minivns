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
@EqualsAndHashCode(of = {"id", "title"})
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

    @Column(name = "done_date")
    private LocalDateTime doneDate;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "max_mark")
    private Integer maxMark;

    @Column(name = "is_done")
    private Boolean isDone;

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

    public Task(@NonNull String title, String link, LocalDateTime deadline,LocalDateTime doneDate, Integer mark, Integer maxMark, @NonNull Subject subjectBySubjectId, @NonNull User user) {
        this.title = title;
        this.link = link;
        this.deadline = deadline;
        this.subjectBySubjectId = subjectBySubjectId;
        this.isDone =false;
        this.mark=mark;
        this.maxMark=maxMark;
        this.doneDate=doneDate;
        this.user = user;
    }

//    @NonNull
//    @Column(name = "user_id")
//    private int userId;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subjectBySubjectId;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
