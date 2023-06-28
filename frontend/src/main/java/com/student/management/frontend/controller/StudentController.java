package com.student.management.frontend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoang.apacheclient.MyClient;
import com.opencsv.CSVWriter;
import com.student.management.frontend.model.Student;
import com.student.management.frontend.model.StudentWrapper;
import com.student.management.frontend.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    MyClient myClient = new MyClient();

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Utils utils;

    @GetMapping("students")
    String getStudent(Model model,
                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                      @RequestParam(value = "sort", defaultValue = "id") String sort) throws Exception {

        String baseUrl = "http://localhost:8081/students";

        String url = utils.buildUrl(baseUrl, "page", page, "sort", sort);

        String resultMapString = myClient.doGet(url);

        int pageSize = 20;
        utils.pagingHelper(model, resultMapString, page, pageSize);
        return "list-students";
    }

    @GetMapping("/students/search")
    public String searchStudents(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "gender", required = false) String gender,
                                 @RequestParam(value = "score", required = false) Integer score,
                                 @RequestParam(value = "classId", required = false) String classId,
                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                 Model model) throws Exception {

        String baseUrl = "http://localhost:8081/students/search/example";

        String url = utils.buildUrl(baseUrl, "id", id, "firstName", firstName, "lastName",
                lastName, "email", email, "gender", gender, "score", score, "classId", classId, "page", page);

//        System.out.println(url);

        String resultMapString = myClient.doGet(url);
        Map<String, Object> resultMap = mapper.readValue(resultMapString,
                new TypeReference<Map<String, Object>>() {});

        List<Student> students = mapper.convertValue(resultMap
                .get("students"), new TypeReference<List<Student>>() {});

        model.addAttribute("students", students);

        return "search-students";
    }

    @PostMapping("students")
    public String addStudent(@ModelAttribute("student") Student student) throws Exception {
        String url = "http://localhost:8081/students";
        String resultMapString = myClient.doPost(url, student);
        return "redirect:/students";
    }

    @PostMapping("students/save-batch")
    public String saveStudentInBatch(@ModelAttribute("student") StudentWrapper studentWrapper) throws Exception {
        List<Student> students = studentWrapper.getStudents();
        String url = "http://localhost:8081/students/save-batch";
        String resultMapString = myClient.doPost(url, students);
        return "redirect:/students";
    }

    @PostMapping("students/{id}/update")
    public String updateStudent(@PathVariable("id") Long id,
                                @ModelAttribute("student") Student student) throws Exception {
        String url = "http://localhost:8081/students" + "/" + id;
        String resultMapString = myClient.doPut(url, student);

        return "redirect:/students";
    }

    @GetMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) throws Exception {
        String url = "http://localhost:8081/students" + "/" + id;
        myClient.doDelete(url);
        return "redirect:/students";
    }

    @GetMapping("students/create")
    public String showFormForAdd(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create-student";
    }

    @GetMapping("students/{id}/edit")
    public String showFormForUpdate(Model model, @PathVariable("id") Long id) throws Exception {
        String url = "http://localhost:8081/students" + "/" + id;
        String studentString = myClient.doGet(url);
        Student student = mapper.readValue(studentString, Student.class);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("students/upload")
    public String uploadStudents(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        StudentWrapper studentWrapper = new StudentWrapper();
        List<Student> students = new ArrayList<>();
        studentWrapper.setStudents(students);

        utils.uploadCSVHelper(file, students);
        model.addAttribute("studentWrapper", studentWrapper);

        return "upload-students";
    }

    @GetMapping("students/export")
    public void exportToCsv(HttpServletResponse response) throws Exception {

        String resultMapString = myClient.doGet("http://localhost:8081/students/export");

        Map<String, Object> resultMap = new ObjectMapper().readValue(resultMapString,
                new TypeReference<Map<String, Object>>() {
                });
        List<Student> students = mapper.convertValue(resultMap
                .get("students"), new TypeReference<List<Student>>() {
        });

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"students-output.csv\"");

        CSVWriter csvWriter = new CSVWriter(response.getWriter());
        String[] headers = {"Id", "First Name", "Last Name", "Email", "Gender", "Score", "Class Id"};
        csvWriter.writeNext(headers);

        for (Student student : students) {
            String[] data = {String.valueOf(student.getId()), student.getFirstName(), student.getLastName(),
                    student.getEmail(), student.getGender(), String.valueOf(student.getScore()),
                    String.valueOf(student.getClassId())};
            csvWriter.writeNext(data);
        }

        csvWriter.close();
    }


    @GetMapping("demo")
    public String demo(Model model) {


        List<Student> listStudents = new ArrayList<>();
        listStudents.add(new Student(1L, "Hoang", "nguyen", "hoang@gmail.com", "male", 10, "100"));
        listStudents.add(new Student(1L, "Hoang", "nguyen", "hoang@gmail.com", "male", 10, "100"));
        listStudents.add(new Student(1L, "Hoang", "nguyen", "hoang@gmail.com", "male", 10, "100"));
        listStudents.add(new Student(1L, "Hoang", "nguyen", "hoang@gmail.com", "male", 10, "100"));

        model.addAttribute("currentPage", 1);

        model.addAttribute("totalPages", 4);

        model.addAttribute("startCount", 1);

        model.addAttribute("endCount", 1);

        model.addAttribute("totalItems", 1);

        model.addAttribute("listStudents", listStudents);

        model.addAttribute("keyword", "keyword");

        return "demo";
    }

}
