package com.educoach.teachers;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeacherRepository implements PanacheRepositoryBase<TeacherEntity, Long> {
    public Boolean existsById(Long id) {
        return count("id", id) > 0;
    }
}
