package com.example.batch_mybatis.batch.reader;

import com.example.batch_mybatis.model.Student;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class BatchItemReader {

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Bean
    public FlatFileItemReader<Student> flatFileItemReader() {
        FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(
                new FileSystemResource(new File("input-file/students-data.csv"))
        );

        DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("id", "first_name", "last_name", "email", "gender", "score", "class_id");
//        delimitedLineTokenizer.setNames("first_name", "last_name", "email", "gender", "score", "class_id");
//        delimitedLineTokenizer.setNames("id", "username", "password");

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);

        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        flatFileItemReader.setLineMapper(defaultLineMapper);

        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

    @Bean
//    @StepScope
    public MyBatisCursorItemReader<Student> myBatisCursorItemReader() {
        MyBatisCursorItemReader<Student> myBatisCursorItemReader = new MyBatisCursorItemReader<>();
        try {
            myBatisCursorItemReader.setQueryId("com.example.batch_mybatis.mapper.StudentMapper.getStudents");
            myBatisCursorItemReader.setSqlSessionFactory(sqlSessionFactory);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myBatisCursorItemReader;
    }
}
