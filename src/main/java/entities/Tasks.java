package entities;

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
public class Tasks {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

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

    @NonNull
    @Column(name = "user_id")
    private int userId;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subjects subjectsBySubjectId;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private States statesByStateId;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users usersByUserId;
}
