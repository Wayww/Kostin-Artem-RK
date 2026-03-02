package com.example.rk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long universityId;

    @NotNull
    private Long subjectId;
}
