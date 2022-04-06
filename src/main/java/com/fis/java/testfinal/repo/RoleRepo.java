package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
}
