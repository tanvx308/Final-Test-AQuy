package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByIdentityNo(String identityNo);
}
