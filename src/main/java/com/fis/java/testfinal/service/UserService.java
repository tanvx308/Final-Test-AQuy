package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User employee);
}
