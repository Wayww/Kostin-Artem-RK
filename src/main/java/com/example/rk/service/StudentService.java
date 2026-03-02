package com.example.rk.service;

import com.example.rk.dto.StudentRequest;
import com.example.rk.entity.Student;
import com.example.rk.entity.Subject;
import com.example.rk.entity.University;
import com.example.rk.repository.StudentRepository;
import com.example.rk.repository.SubjectRepository;
import com.example.rk.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final SubjectRepository subjectRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Student create(StudentRequest request) {
        Student student = new Student();
        applyRequest(student, request);
        return saveStudent(student);
    }

    public Student update(Long id, StudentRequest request) {
        Student student = findById(id);
        applyRequest(student, request);
        return saveStudent(student);
    }

    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    private void applyRequest(Student student, StudentRequest request) {
        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "University not found"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject not found"));

        student.setName(request.getName());
        student.setUniversity(university);
        student.setSubject(subject);
    }

    private Student saveStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subject already assigned to another student");
        }
    }
}
