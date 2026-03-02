package com.example.rk.controller;

import com.example.rk.dto.UniversityRequest;
import com.example.rk.entity.University;
import com.example.rk.service.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public List<University> findAll() {
        return universityService.findAll();
    }

    @GetMapping("/{id}")
    public University findById(@PathVariable Long id) {
        return universityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public University create(@Valid @RequestBody UniversityRequest request) {
        return universityService.create(request);
    }

    @PutMapping("/{id}")
    public University update(@PathVariable Long id, @Valid @RequestBody UniversityRequest request) {
        return universityService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        universityService.delete(id);
    }
}
