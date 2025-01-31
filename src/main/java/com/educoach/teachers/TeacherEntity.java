package com.educoach.teachers;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Entity(name = "Teacher")
@Table(name = "teachers")
public class TeacherEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @CreationTimestamp
    @Column(name = "creation_date")
    public Date creationDate;

    @Column(name = "date_of_employment")
    public Date dateOfEmployment;

    @Column(name = "weekly_hours")
    public Integer weeklyHours;

    @Column(name = "birthday")
    public Date birthday;
}
