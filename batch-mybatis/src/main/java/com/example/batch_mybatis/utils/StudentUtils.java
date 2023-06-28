package com.example.batch_mybatis.utils;

import com.example.batch_mybatis.jasperreport.StudentReport;
import com.example.batch_mybatis.mapper.StudentMapper;
import net.sf.jasperreports.engine.JRException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentUtils {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("jobInsertStudents")
    Job jobInsertStudents;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentReport studentReport;

    public void startJob() throws Exception {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("Current time", new JobParameter(new Date()));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = null;
        jobExecution = jobLauncher.run(jobInsertStudents, jobParameters);
//        System.out.println("Job ID: " + jobExecution.getId());
    }

    public void seedData() throws Exception {
        Integer databaseIsNull = studentMapper.checkDatabaseNull();
        if (databaseIsNull == null || databaseIsNull == 0) {
            startJob();
        } else {
            System.out.println("Database is not empty, don't seed data");
        }
    }

    public void makeStudentReport() throws JRException {
        Integer databaseIsNull = studentMapper.checkDatabaseNull();
        if (databaseIsNull != null) {
            studentReport.makeReports();
        } else {
            System.out.println("Data is empty, don't make report");
        }
    }
}
