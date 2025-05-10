package com.wtwlc.web_experiment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@MapperScan("com.wtwlc.web_experiment.mapper")
public class WebExperimentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebExperimentApplication.class, args);
    }

}
