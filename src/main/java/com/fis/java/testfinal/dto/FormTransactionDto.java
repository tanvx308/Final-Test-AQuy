package com.fis.java.testfinal.dto;

import com.fis.java.testfinal.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormTransactionDto {
    @NotNull(message = "TXN001: From Account is not valid.")
    private Account fromAccount;
    @NotNull(message = "TXN002: To Account is not valid.")
    private Account toAccount;
    @NotNull(message = "TXN003: Amount shoule not be null.")
    @Min(value = 1000, message = "TXN004: Amount should not be less than 1000.")
    private Double amount;
    private String content;
}
