package com.example.batch_mybatis;

import com.example.batch_mybatis.democontroller.StudentControllerForDemo;
import com.example.batch_mybatis.mapper.StudentMapper;
import com.example.batch_mybatis.utils.StudentUtils;
import net.sf.jasperreports.engine.JRException;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class BatchMybatisApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(BatchMybatisApplication.class);


	@Autowired
	StudentUtils studentUtils;

	public static void main(String[] args) {
		SpringApplication.run(BatchMybatisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentUtils.seedData();
		studentUtils.makeStudentReport();
	}

//	@Scheduled(cron = "0 0/1 * 1/1 * ?") // 1 minute
	@Scheduled(cron = "0 0/10 * 1/1 * ?") // 10 minutes
	public void makeStudentsReport() throws JRException {
		logger.info("Make reports each 10 minutes");
		studentUtils.makeStudentReport();
	}
}
