package com.example.rk.service;

import com.example.rk.dto.UniversityRequest;
import com.example.rk.entity.University;
import com.example.rk.repository.StudentRepository;
import com.example.rk.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final StudentRepository studentRepository;

    public List<University> findAll() {
        return universityRepository.findAll();
    }

    public University findById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found"));
    }

    public University create(UniversityRequest request) {
        University university = new University();
        university.setName(request.getName());
        return universityRepository.save(university);
    }

    public University update(Long id, UniversityRequest request) {
        University university = findById(id);
        university.setName(request.getName());
        return universityRepository.save(university);
    }

    public void delete(Long id) {
        University university = findById(id);
        if (studentRepository.existsByUniversity_Id(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "University has students");
        }
        universityRepository.delete(university);
    }
}
