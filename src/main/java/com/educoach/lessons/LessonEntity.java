package com.educoach.lessons;

import com.educoach.subjects.SubjectEntity;
import com.educoach.teachers.TeacherEntity;
import com.educoach.topics.TopicEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "Lesson")
@Table(name = "lessons")
public class LessonEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    public TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    public TopicEntity topic;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    public SubjectEntity subject;
    
}
