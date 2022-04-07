package com.fis.java.testfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestFinalApplication{

    public static void main(String[] args) {
        SpringApplication.run(TestFinalApplication.class, args);
    }

}
