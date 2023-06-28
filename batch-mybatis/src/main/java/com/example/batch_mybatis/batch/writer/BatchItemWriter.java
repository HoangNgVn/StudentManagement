package com.example.batch_mybatis.batch.writer;

import com.example.batch_mybatis.model.Student;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchItemWriter {

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Bean
//    @StepScope
    public MyBatisBatchItemWriter<Student> MyBatisBatchItemWriter() {
        MyBatisBatchItemWriter<Student> myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
        myBatisBatchItemWriter.setStatementId("com.example.batch_mybatis.mapper.StudentMapper.addStudent");
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        return myBatisBatchItemWriter;
    }

}
