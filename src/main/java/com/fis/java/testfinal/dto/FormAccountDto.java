package com.fis.java.testfinal.dto;

import com.fis.java.testfinal.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormAccountDto {
    @NotNull(message = "Account Number should not be null.")
    @Length(max = 13, message = "ACC002: Account Number`s length is 13")
    private String accountNumber;

    @NotNull(message = "Account Balance should not be null.")
    @Min(value = 0)
    private Double balance;

   @NotNull(message = "Customer is not valid.")
    private Customer customer;
}
