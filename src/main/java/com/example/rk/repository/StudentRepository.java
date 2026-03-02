package com.example.rk.repository;

import com.example.rk.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByUniversity_Id(Long universityId);

    boolean existsBySubject_Id(Long subjectId);
}
