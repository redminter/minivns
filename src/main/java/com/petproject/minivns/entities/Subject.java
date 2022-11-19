package com.petproject.minivns.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subjects")
@EqualsAndHashCode(of = {"id", "name"})
public class Subject {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjects_start12")
    @SequenceGenerator(name = "subjects_start12", allocationSize = 1)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NonNull
    @Column(name = "title")
    private String title;

    @Column(name = "vns_url")
    private String vnsUrl;

    @Column(name = "lection_url")
    private String lectionUrl;

    @Column(name = "prakt_url")
    private String praktUrl;

    @Column(name = "lab_url")
    private String labUrl;

    public Subject(@NonNull String title, String vnsUrl, String lectionUrl, String praktUrl, String labUrl) {
        this.title = title;
        this.vnsUrl = vnsUrl;
        this.lectionUrl = lectionUrl;
        this.praktUrl = praktUrl;
        this.labUrl = labUrl;
    }

    public Subject(Integer id, @NonNull String title, String vnsUrl, String lectionUrl, String praktUrl, String labUrl) {
        this.id = id;
        this.title = title;
        this.vnsUrl = vnsUrl;
        this.lectionUrl = lectionUrl;
        this.praktUrl = praktUrl;
        this.labUrl = labUrl;
    }


//    @OneToMany(mappedBy = "task_id")
//    private List<Task> taskById;

}
