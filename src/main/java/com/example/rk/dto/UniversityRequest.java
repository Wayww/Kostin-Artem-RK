package com.example.rk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityRequest {

    @NotBlank
    private String name;
}
