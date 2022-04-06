package com.fis.java.testfinal.controller;

import com.fis.java.testfinal.dto.FormCustomerDto;
import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.service.CustomerService;
import com.fis.java.testfinal.utils.CustomerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Repository
@RequestMapping("/api")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<?> findCustomers(@RequestParam("page")Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6), sort);
        return ResponseEntity.ok(customerService.findAllCustomer(pageable));
    }

    @PostMapping("/customer/save")
    public ResponseEntity<?> saveCustomer(@Validated @RequestBody FormCustomerDto dto){
        Customer customer = CustomerUtil.convertFromDto(dto);
        customer = customerService.createCustomer(customer);
        log.info("Customer with id: {} created", customer.getId());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/customer/update/{id}")
    public ResponseEntity<?> updateCustomer(@Validated @RequestBody FormCustomerDto dto,
                                            @PathVariable("id") Optional<Long> id){
        Customer customer = CustomerUtil.convertFromDto(dto);
        customer = customerService.updateCustomer(customer, id.orElse(null));
        log.info("Customer with id: {} updated", customer.getId());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") Optional<Long> id){
        Customer customer = customerService.findCustomerById(id.orElse(null));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/search")
    public ResponseEntity<?> findCustomerByKeyword(){
        return null;
    }
}
