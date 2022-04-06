package com.fis.java.testfinal;

import com.fis.java.testfinal.entity.Role;
import com.fis.java.testfinal.entity.User;
import com.fis.java.testfinal.repo.RoleRepo;
import com.fis.java.testfinal.repo.UserRepo;
import com.fis.java.testfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class TestFinalApplication{

    public static void main(String[] args) {
        SpringApplication.run(TestFinalApplication.class, args);
    }

}
