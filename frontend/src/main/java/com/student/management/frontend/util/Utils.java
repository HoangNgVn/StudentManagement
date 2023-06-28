package com.student.management.frontend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.student.management.frontend.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
public class Utils {

    ObjectMapper mapper = new ObjectMapper();

    public List<Student> convertStringToStudents(String studentsString) throws JsonProcessingException {
        return mapper.readValue(studentsString, new TypeReference<List<Student>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
    }

    public String buildUrl(String baseUrl, Object... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);

        if (params != null && params.length % 2 == 0) {
            for (int i = 0; i < params.length; i += 2) {
                String paramName = String.valueOf(params[i]);
                Object paramValue = params[i + 1];

                if (paramValue != null && !paramValue.toString().isEmpty()) {
                    builder.queryParam(paramName, paramValue);
                }
            }
        }

        return builder.toUriString();
    }

    public void pagingHelper(Model model, String resultMapString, Integer page, int pageSize) throws JsonProcessingException {

        Map<String, Object> resultMap = mapper.readValue(resultMapString,
                new TypeReference<Map<String, Object>>() {});

        List<Student> students = mapper.convertValue(resultMap
                .get("studentsPage"), new TypeReference<List<Student>>() {});

        int totalElements = (int) resultMap.get("numberOfStudents");
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        totalPages = totalPages == 0 ? 1 : totalPages;

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("students", students);
    }

    public void uploadCSVHelper(MultipartFile file, List<Student> students) throws IOException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> lines = reader.readAll();
            boolean skipFirstLine = true;

            for (String[] line : lines) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue; // skip the 1st line
                }

                if (line.length == 7) {
                    Long id = Long.valueOf(line[0]);
                    String firstName = line[1];
                    String lastName = line[2];
                    String email = line[3];
                    String gender = line[4];
                    Integer score = Integer.valueOf(line[5]);
                    String classId = line[6];
                    Student student = new Student(id, firstName, lastName, email, gender, score, classId);
                    students.add(student);
                }
            }
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
