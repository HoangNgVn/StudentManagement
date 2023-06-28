package com.example.backend.services;


import com.example.backend.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Map<String, Object> getStudents(int page, int pageSize, java.lang.String sort);

    Map<String, Object> getStudentsForExport();

    Student getStudentById(Long id);

    Map<String, Object> searchStudents(String isCaseSensitive, Long id,
                                       String firstName, String lastName,
                                       String email, String gender,
                                       Integer score, String classId
    );

    Student addStudent(Student student);

    List<Student> saveStudentsInBatch(List<Student> students);

    Student updateStudent(Long id, Student student);

    ResponseEntity<List<Student>> deleteStudent(Long id);

}
