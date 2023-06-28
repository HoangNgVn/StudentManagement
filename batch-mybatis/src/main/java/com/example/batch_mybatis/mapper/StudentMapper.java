package com.example.batch_mybatis.mapper;

import com.example.batch_mybatis.model.ClassStatistics;
import com.example.batch_mybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    public int addStudent(Student student);
    public List<Student> getStudents();

    public List<Student> get5HighestStudents();
    public List<Student> get5LowestStudents();

    @Select("SELECT COUNT(*) FROM students WHERE score BETWEEN 0 AND 5")
    public Long getStudentsBetween0and5();

    @Select("SELECT COUNT(*) FROM students WHERE score BETWEEN 6 AND 8")
    public Long getStudentsBetween6and8();

    @Select("SELECT COUNT(*) FROM students WHERE score BETWEEN 9 AND 10")
    public Long getStudentsBetween9and10();

    @Select("SELECT 1 FROM students LIMIT 1")
    public Integer checkDatabaseNull();

    List<ClassStatistics> getClassStatistics();
}
