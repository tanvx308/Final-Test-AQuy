package com.fis.java.testfinal.dto;

import com.fis.java.testfinal.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormTransactionDto {
    @NotNull(message = "From Account is not valid.")
    private Account fromAccount;

    @NotNull(message = "To Account is not valid.")
    private Account toAccount;

    @NotNull(message = "Amount shoule not be null.")
    @Min(value = 1000, message = "Amount should not be less than 1000.")
    private Double amount;

    private String content;
}
