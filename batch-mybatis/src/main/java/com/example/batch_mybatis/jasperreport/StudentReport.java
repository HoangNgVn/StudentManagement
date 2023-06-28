package com.example.batch_mybatis.jasperreport;

import com.example.batch_mybatis.mapper.StudentMapper;
import com.example.batch_mybatis.model.ClassStatistics;
import com.example.batch_mybatis.model.ScoreStatistics;
import com.example.batch_mybatis.model.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentReport {

    @Autowired
    StudentMapper studentMapper;

    public void makeReports() throws JRException {
        makeTop5HighestScoreStudentsReport();
        makeTop5LowestScoreStudentsReport();
        makeScoreStatisticOfStudents();
        makeClassStatisticOfStudents();
    }

    public void makeTop5HighestScoreStudentsReport() throws JRException {
        List<Student> highestScoreStudents = studentMapper.get5HighestStudents();
        makeReport(highestScoreStudents, "top5HighestScoreStudents");
    }

    public void makeTop5LowestScoreStudentsReport() throws JRException {
        List<Student> lowestScoreStudents = studentMapper.get5LowestStudents();
        makeReport(lowestScoreStudents, "top5LowestScoreStudents");
    }

    public void makeScoreStatisticOfStudents() throws JRException {
        List<ScoreStatistics> scoreOfStudents = new ArrayList<>();
        scoreOfStudents.add(new ScoreStatistics("0-5", studentMapper.getStudentsBetween0and5()));
        scoreOfStudents.add(new ScoreStatistics("6-8", studentMapper.getStudentsBetween6and8()));
        scoreOfStudents.add(new ScoreStatistics("9-10", studentMapper.getStudentsBetween9and10()));
        makeReport(scoreOfStudents, "scoreStatistics");
    }

    public void makeClassStatisticOfStudents() throws JRException {
        List<ClassStatistics> classStatisticsList = studentMapper.getClassStatistics();
        makeReport(classStatisticsList, "classStatistics");
    }

    public <T> void makeReport(List<T> datasourceRaw, String fileName) throws JRException {

        String inputFilePath = "input-file/" + fileName + ".jrxml";
        String outputFilePath = "output-file/" + fileName + ".pdf";
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(datasourceRaw);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(fileName, dataSource);

        JasperReport report = JasperCompileManager.compileReport(inputFilePath);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(print, outputFilePath);
        System.out.println(fileName + " report Created ...");
    }

}
