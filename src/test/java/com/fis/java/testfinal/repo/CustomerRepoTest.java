package com.fis.java.testfinal.repo;

import com.fis.java.testfinal.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @Test
    void givenIdentityNo_whenFindByIdentityNo_thenReturnCustomer() {
        String identityNo = "1234567899";
        Customer customer = customerRepo.findByIdentityNo(identityNo);

        assertThat(customer).isNotNull();
    }
}