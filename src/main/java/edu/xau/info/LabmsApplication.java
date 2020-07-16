package edu.xau.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("edu.xau.info.mapper")
@EnableScheduling
public class LabmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabmsApplication.class, args);
    }

}
