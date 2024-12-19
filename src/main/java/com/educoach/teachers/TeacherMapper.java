package com.educoach.teachers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface TeacherMapper {
    Teacher toDomain(TeacherEntity entity);
    TeacherEntity toEntity(Teacher domain);
}
