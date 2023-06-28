package com.example.backend.services;

import com.example.backend.models.Student;
import com.example.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Map<String, Object> getStudents(int page, int pageSize, String sort) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Student> studentsEachPage = studentRepository.findAll(PageRequest.of(page, pageSize, Sort.by(sort).ascending()));
        resultMap.put("numberOfStudents", studentRepository.findAll().size());
        resultMap.put("studentsPage", studentsEachPage.getContent());
        return resultMap;
    }

    public Map<String, Object> getStudentsForExport() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("students", studentRepository.findAll());
        return resultMap;
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Map<String, Object> searchStudents(String isCaseSensitive, Long id,
                                              String firstName, String lastName,
                                              String email, String gender,
                                              Integer score, String classId
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Student> students = new ArrayList<>();
        if (isCaseSensitive.equals("caseSensitive")) {
            System.out.println("Case Sensitive -->" + firstName);
            students = studentRepository.filterByMultipleFieldsCaseSensitive(id, firstName, lastName, email, gender, score, classId);
        } else if (isCaseSensitive.equals("inCaseSensitive")) {
            System.out.println("InCase Sensitive JPQL");
            students = studentRepository.filterByMultipleFieldsInCaseSensitive(id, firstName, lastName, email, gender, score, classId);
        } else {
            System.out.println("InCase Sensitive Example");
            Student student = new Student(id, firstName, lastName, email, gender, score, classId);
            ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
                    .withMatcher("id", exact())
                    .withMatcher("firstName", contains())
                    .withMatcher("lastName", contains())
                    .withMatcher("email", contains())
                    .withMatcher("gender", contains())
                    .withMatcher("score", exact())
                    .withMatcher("classId", contains());

            Example<Student> example = Example.of(student, matcher);
            List<Student> studentsInPage = studentRepository.findAll(example, Sort.by("id").ascending());
            resultMap.put("students", studentsInPage);
        }
        return resultMap;
    }

    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> saveStudentsInBatch(List<Student> students) {
        studentRepository.saveAll(students);
        return students;
    }

    public Student updateStudent(Long id, Student student) {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Invalid student ID: " + id);
        }
    }

    public ResponseEntity<List<Student>> deleteStudent( Long id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.NOT_FOUND);
        }
    }
}
