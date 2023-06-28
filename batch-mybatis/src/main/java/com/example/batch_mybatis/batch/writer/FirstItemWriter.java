package com.example.batch_mybatis.batch.writer;

import com.example.batch_mybatis.model.Student;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Student> {
    @Override
    public void write(List<? extends Student> list) throws Exception {
        System.out.println("Inside Item Writer");
        list.forEach(System.out::println);
    }
}
