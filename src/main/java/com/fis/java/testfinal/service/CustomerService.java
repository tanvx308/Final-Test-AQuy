package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.exception.NotValidCustomerException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll(Pageable pageable);
    Customer create(Customer customer) throws NotValidCustomerException;
    Customer update(Customer customer) throws NotValidCustomerException;
    void delete(Long id) throws NotValidCustomerException;
    Customer findById(Long id) throws NotValidCustomerException;
}
