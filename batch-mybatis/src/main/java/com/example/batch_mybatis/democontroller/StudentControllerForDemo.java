package com.example.batch_mybatis.democontroller;

import com.example.batch_mybatis.jasperreport.StudentReport;
import com.example.batch_mybatis.mapper.StudentMapper;
import com.example.batch_mybatis.model.Student;

import com.example.batch_mybatis.utils.StudentUtils;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentControllerForDemo {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentReport studentReport;

    @Autowired
    StudentUtils studentUtils;

    Logger logger = LoggerFactory.getLogger(StudentControllerForDemo.class);

    @GetMapping("students")
    public List<Student> getStudents() {
        return studentMapper.getStudents();
    }

    @GetMapping("seedData")
    public List<Student> putAndGetStudents() throws Exception {
        studentUtils.seedData();
        return getStudents();
    }


    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void testSchedule() {
//        logger.info("This is schedule");
    }

    @GetMapping("students/report")
    public void getReport() throws JRException {

    }
}
