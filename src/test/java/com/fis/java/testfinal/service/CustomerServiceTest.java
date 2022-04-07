package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Customer;
import com.fis.java.testfinal.repo.CustomerRepo;
import com.fis.java.testfinal.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepo customerRepo;

    @InjectMocks
    @Autowired
    CustomerServiceImpl customerService;

    Customer customer;

    List<Customer> customers;

    @BeforeEach
    public void setUp(){
        customer = Customer.builder()
                .id(1L)
                .address("HN")
                .birthday(LocalDateTime.now().minusDays(365 * 30))
                .createDateTime(LocalDateTime.now())
                .customerType(Customer.CustomerType.INDIVIDUAL)
                .identityNo("1234567899")
                .mobile("0987654321")
                .name("Tan Vu")
                .status(1)
                .updateDateTime(LocalDateTime.now())
                .build();

        customers = new ArrayList<>();
        customers.add(customer);
    }

    @AfterEach
    public void tearDown(){
        customers = null;
        customer = null;
    }

    @Test
    void whenFindAllCustomer_thenReturnCustomerList() {
        Page<Customer> page = new PageImpl<>(customers);

        when(customerRepo.findAll(page.getPageable())).thenReturn(page);

        List<Customer> list = customerService.findAllCustomer(page.getPageable());

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void givenCustomer_whenCreateCustomer_thenReturnCustomer() {
        when(customerRepo.save(customer)).thenReturn(customer);

        Customer cus = customerService.createCustomer(customer);

        assertThat(cus.getId()).isEqualTo(customer.getId());
    }

    @Test
    void givenCustomerAndId_whenUpdateCustomer_thenReturnCustomer() {
        when(customerRepo.existsById(customer.getId())).thenReturn(true);
        when(customerRepo.save(customer)).thenReturn(customer);

        Customer cus = customerService.updateCustomer(customer, customer.getId());

        assertThat(cus.getId()).isEqualTo(customer.getId());
    }

    @Test
    void givenCustomerId_whenFindCustomerById_thenReturnCustomer() {
        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));

        Customer cus = customerService.findCustomerById(customer.getId());

        assertThat(cus.getId()).isEqualTo(customer.getId());
    }
}