package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByIdentityNo(String identityNo);

    @Query(name = "searchByKeyword")
    List<Customer> searchByKeyword(@Param("key")String key, @Param("word")String word);
}
