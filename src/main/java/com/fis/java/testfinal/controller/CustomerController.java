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

import java.util.List;
import java.util.Optional;

@Repository
@RequestMapping("/api")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //api Liệt kê danh sách khách hàng
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findCustomers(@RequestParam("page")Optional<Integer> page,
                                              @RequestParam("size") Optional<Integer> size){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6), sort);
        return ResponseEntity.ok(customerService.findAllCustomer(pageable));
    }

    // api Thêm mới một khách hàng
    @PostMapping("/customer/save")
    public ResponseEntity<Customer> saveCustomer(@Validated @RequestBody FormCustomerDto dto){
        Customer customer = CustomerUtil.convertFromDto(dto);
        customer = customerService.createCustomer(customer);
        log.info("Customer with id: {} created", customer.getId());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    //api Cập nhật thông tin khách hàng theo ID.
    @PostMapping("/customer/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@Validated @RequestBody FormCustomerDto dto,
                                            @PathVariable("id") Optional<Long> id){
        Customer customer = CustomerUtil.convertFromDto(dto);
        customer = customerService.updateCustomer(customer, id.orElse(null));
        log.info("Customer with id: {} updated", customer.getId());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    //api Tìm kiếm khách hàng theo ID
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") Optional<Long> id){
        Customer customer = customerService.findCustomerById(id.orElse(null));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //api Tìm kiếm khách hàng
    //theo tên, số điện thoại, số cmt/căn cước, trạng thái, loại khách hàng
    @GetMapping("/customer/search")
    public ResponseEntity<List<Customer>> findCustomerByKeyword(@RequestParam("keyword") String keyword){
        return new ResponseEntity<>(customerService.searchByKeyword(keyword), HttpStatus.OK);
    }
}
