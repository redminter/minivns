package com.petproject.minivns.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subjects")
@EqualsAndHashCode(of = {"id", "title"})
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

    @Column(name ="at_monday")
    private boolean atMonday;

    @Column(name ="at_tuesday")
    private boolean atTuesday;

    @Column(name ="at_wednesday")
    private boolean atWednesday;

    @Column(name ="at_thursday")
    private boolean atThursday;

    @Column(name ="at_friday")
    private boolean atFriday;
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

    public Subject(@NonNull String title, String vnsUrl, String lectionUrl, String praktUrl, String labUrl, boolean atMonday, boolean atTuesday, boolean atWednesday, boolean atThursday, boolean atFriday) {
        this.title = title;
        this.vnsUrl = vnsUrl;
        this.lectionUrl = lectionUrl;
        this.praktUrl = praktUrl;
        this.labUrl = labUrl;
        this.atMonday = atMonday;
        this.atTuesday = atTuesday;
        this.atWednesday = atWednesday;
        this.atThursday = atThursday;
        this.atFriday = atFriday;
    }
//    @OneToMany(mappedBy = "task_id")
//    private List<Task> taskById;

}
