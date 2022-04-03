package com.fis.java.testfinal.controller;

import com.fis.java.testfinal.dto.FormCustomerDto;
import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.exception.NotValidCustomerException;
import com.fis.java.testfinal.service.CustomerService;
import com.fis.java.testfinal.utils.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Repository
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<?> findCustomers(@RequestParam("page")Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        return ResponseEntity.ok(customerService.findAll(pageable));
    }

    @PostMapping("/customer/save")
    public ResponseEntity<?> saveCustomer(@RequestBody FormCustomerDto dto) throws NotValidCustomerException {
        Customer customer = CustomerUtil.convertFromDto(dto);
        return ResponseEntity.ok(customerService.create(customer));
    }
}
