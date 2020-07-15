package edu.xau.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.xau.info.mapper")
public class LabmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabmsApplication.class, args);
    }

}
