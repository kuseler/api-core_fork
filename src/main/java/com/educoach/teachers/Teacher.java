package com.educoach.teachers;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public record Teacher(
        Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        Date creationDate,
        Date dateOfEmployment,
        Integer weeklyHours,
        Date birthday
){
}
