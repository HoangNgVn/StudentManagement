package com.example.batch_mybatis.batch;

import com.example.batch_mybatis.model.Student;
import com.example.batch_mybatis.batch.writer.FirstItemWriter;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    FlatFileItemReader<Student> flatFileItemReader;
    @Autowired
    MyBatisBatchItemWriter<Student> myBatisBatchItemWriter;

//    @Autowired
//    FirstItemWriter firstItemWriter;

    @Bean
    public Job jobInsertStudents() {
        return jobBuilderFactory.get("jobInsertStudents")
                .incrementer(new RunIdIncrementer())
                .flow(stepOneInsert())
                .end()
                .build();
    }

    public Step stepOneInsert() {
        return stepBuilderFactory.get("stepOneInsert")
                .<Student, Student>chunk(10)
                .reader(flatFileItemReader)
                .writer(myBatisBatchItemWriter)
//                .writer(firstItemWriter)
                .build();
    }
}
