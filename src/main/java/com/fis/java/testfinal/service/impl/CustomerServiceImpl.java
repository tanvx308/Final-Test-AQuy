package com.fis.java.testfinal.service.impl;

import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.exception.AppException;
import com.fis.java.testfinal.model.ErrorMessage;
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
            throw new AppException("Customer", new ErrorMessage("CUS400", "Customer is exist"));
        }
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        if(!customerRepo.existsById(id)){
            throw new AppException("Customer", new ErrorMessage("CUS404", "Customer is not exist"));
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
           throw new AppException("Customer", new ErrorMessage("CUS404", "Customer is not exist"));
       }
    }

    @Override
    public List<Customer> searchByKeyword(String keyword) {
        String key = keyword;
        String word = "%" + keyword + "%";
        return customerRepo.searchByKeyword(key, word);
    }
}
