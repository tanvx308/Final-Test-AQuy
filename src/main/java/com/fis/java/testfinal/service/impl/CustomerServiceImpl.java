package com.fis.java.testfinal.service.impl;

import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.exception.ResourceExistException;
import com.fis.java.testfinal.exception.ResourceNotFoundException;
import com.fis.java.testfinal.repo.CustomerRepo;
import com.fis.java.testfinal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> findAllCustomer(Pageable pageable) {
        return customerRepo.findAll(pageable).getContent();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer ctm = customerRepo.findByIdentityNo(customer.getIdentityNo());
        if(ctm != null){
            throw new ResourceExistException("CUS405", "Customer", "Identity No", customer.getIdentityNo());
        }
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        if(!customerRepo.existsById(id)){
            throw new ResourceNotFoundException("CUS404", "Customer", "Id", String.valueOf(id));
        }
        customer.setId(id);
        return customerRepo.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
       Optional<Customer> customer = customerRepo.findById(id);
       if(customer.isPresent()){
           return customer.get();
       }else{
           throw new ResourceNotFoundException("CUS404", "Customer", "Id", String.valueOf(id));
       }
    }
}
