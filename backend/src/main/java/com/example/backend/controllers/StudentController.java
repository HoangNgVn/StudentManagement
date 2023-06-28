package com.example.backend.controllers;

import com.example.backend.models.Student;
import com.example.backend.repositories.StudentRepository;
import com.example.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("students")
    public Map<String, Object> getStudents(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                           @RequestParam(value = "sort", defaultValue = "id") String sort
    ) {
        Map<String, Object> resultMap = studentService.getStudents(page, pageSize, sort);
        return resultMap;
    }

    @GetMapping("students/export")
    public Map<String, Object> getStudentsForExport() {
        Map<String, Object> resultMap = studentService.getStudentsForExport();
        return resultMap;
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return student;
    }

    @GetMapping("students/search/{isCaseSensitive}")
    public Map<String, Object> searchStudents(
            @PathVariable(value = "isCaseSensitive", required = false) String isCaseSensitive,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "score", required = false) Integer score,
            @RequestParam(value = "classId", required = false) String classId
//            @RequestParam(value = "page", defaultValue = "0") Integer page

    ) {
        Map<String, Object> resultMap = studentService.searchStudents(
                isCaseSensitive, id, firstName, lastName, email, gender, score, classId);
        return resultMap;
    }

    @PostMapping("students")
    public Student addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return student;
    }

    @PostMapping("students/save-batch")
    public List<Student> saveStudentsInBatch(@RequestBody List<Student> students) {
        studentService.saveStudentsInBatch(students);
        return students;
    }

    @PutMapping("students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<List<Student>> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
