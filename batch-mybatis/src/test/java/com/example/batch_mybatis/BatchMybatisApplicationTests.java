package com.example.batch_mybatis;

import com.example.batch_mybatis.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchMybatisApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testClassStatistics() {
        System.out.println(studentMapper.getClassStatistics());
    }
}