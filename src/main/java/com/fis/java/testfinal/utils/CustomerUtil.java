package com.fis.java.testfinal.utils;

import com.fis.java.testfinal.constant.CustomerStatus;
import com.fis.java.testfinal.dto.FormCustomerDto;
import com.fis.java.testfinal.entity.Customer;

import java.time.LocalDateTime;

public class CustomerUtil {
    public static Customer convertFromDto(FormCustomerDto dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setBirthday(dto.getBirthday());
        customer.setAddress(dto.getAddress());
        customer.setIdentityNo(dto.getIdentityNo());
        customer.setMobile(dto.getMobile());
        customer.setCustomerType(dto.getCustomerType());
        customer.setStatus(CustomerStatus.ACTIVE);
        customer.setCreateDateTime(LocalDateTime.now());
        customer.setUpdateDateTime(LocalDateTime.now());
        return customer;
    }
}