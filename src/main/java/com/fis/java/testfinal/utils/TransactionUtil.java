package com.fis.java.testfinal.utils;

import com.fis.java.testfinal.dto.FormTransactionDto;
import com.fis.java.testfinal.entity.Transaction;

import java.time.LocalDateTime;

public class TransactionUtil {
    public static Transaction convertFromDto(FormTransactionDto dto){
        Transaction transaction = new Transaction();
        transaction.setFromAccount(dto.getFromAccount());
        transaction.setToAccount(dto.getToAccount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(dto.getAmount());
        transaction.setContent(dto.getContent());
        return transaction;
    }
}
