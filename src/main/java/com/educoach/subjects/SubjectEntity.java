package com.educoach.subjects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "Subject")
@Table(name = "subjects")
public class SubjectEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "designation")
    public String designation;

    @Column(name = "color")
    public String color;
}
