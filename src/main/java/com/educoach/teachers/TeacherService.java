package com.educoach.teachers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(final TeacherRepository teacherRepository, final TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Teacher> findById(final Long id) {
        return teacherRepository.findByIdOptional(id).map(teacherMapper::toDomain);
    }

    @Transactional
    public Teacher create(@Valid final Teacher teacher) {
        TeacherEntity entity = teacherMapper.toEntity(teacher);
        entity.id = null;
        teacherRepository.persist(entity);
        return teacherMapper.toDomain(entity);
    }

    @Transactional
    public Teacher update(final Long id, @Valid final Teacher teacher) {
        TeacherEntity entity = teacherRepository.findById(id);
        entity.firstName = teacher.firstName();
        entity.lastName = teacher.lastName();
        teacherRepository.persist(entity);
        return teacherMapper.toDomain(entity);
    }

    @Transactional
    public void delete(final Long id) {
        teacherRepository.deleteById(id);
    }

    public Boolean existsById(final Long id) {
        return teacherRepository.existsById(id);
    }
}
