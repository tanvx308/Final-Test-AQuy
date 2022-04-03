package com.fis.java.testfinal.service.impl;

import com.fis.java.testfinal.constant.CustomerStatus;
import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.exception.NotValidCustomerException;
import com.fis.java.testfinal.repo.CustomerRepo;
import com.fis.java.testfinal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> findAll(Pageable pageable) {
        Page<Customer> customerPage = customerRepo.findAll(pageable);
        return customerPage.getContent();
    }

    @Override
    public Customer create(Customer customer) throws NotValidCustomerException {
        Customer ctm = customerRepo.findByIdentityNo(customer.getIdentityNo());
        if(ctm != null){
            throw new NotValidCustomerException("CUS405", String.format("Customer with Identity No %s is exist.", customer.getIdentityNo()));
        }
        return customerRepo.save(customer);
    }

    @Override
    public Customer update(Customer customer) throws NotValidCustomerException {
        Long id = customer.getId();
        if(!customerRepo.existsById(id)){
            throw new NotValidCustomerException("CUS404", String.format("Customer with id %d is not exist.", id));
        }
        return customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) throws NotValidCustomerException {
        Optional<Customer> customer = customerRepo.findById(id);
        if(!customer.isPresent()){
            throw new NotValidCustomerException("CUS404", String.format("Customer with id %d is not exist.", id));
        }
        Customer ctm = customer.get();
        ctm.setStatus(CustomerStatus.IN_ACTIVE);
        customerRepo.save(ctm);
    }

    @Override
    public Customer findById(Long id) throws NotValidCustomerException {
        if(!customerRepo.existsById(id)){
            throw new NotValidCustomerException("CUS404", String.format("Customer with id %d is not exist.", id));
        }
        return customerRepo.findById(id).orElse(null);
    }
}
