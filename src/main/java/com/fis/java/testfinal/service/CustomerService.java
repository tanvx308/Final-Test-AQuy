package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomer(Pageable pageable);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Long id);

    Customer findCustomerById(Long id);

    List<Customer> searchByKeyword(String keyword);
}
