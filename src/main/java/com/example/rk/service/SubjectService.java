package com.example.rk.service;

import com.example.rk.dto.SubjectRequest;
import com.example.rk.entity.Subject;
import com.example.rk.repository.StudentRepository;
import com.example.rk.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
    }

    public Subject create(SubjectRequest request) {
        Subject subject = new Subject();
        subject.setTitle(request.getTitle());
        return subjectRepository.save(subject);
    }

    public Subject update(Long id, SubjectRequest request) {
        Subject subject = findById(id);
        subject.setTitle(request.getTitle());
        return subjectRepository.save(subject);
    }

    public void delete(Long id) {
        Subject subject = findById(id);
        if (studentRepository.existsBySubject_Id(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject is assigned to student");
        }
        subjectRepository.delete(subject);
    }
}
