package com.educoach.subjects;

import java.util.List;

import com.educoach.topics.TopicEntity;

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

    @ManyToOne
    @JoinColumn(name = "topic_id")
    public TopicEntity topic;
}
