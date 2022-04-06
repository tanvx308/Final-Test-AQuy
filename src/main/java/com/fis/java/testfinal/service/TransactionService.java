package com.fis.java.testfinal.service;

import com.fis.java.testfinal.entity.Transaction;
import com.fis.java.testfinal.model.Report;

import java.util.List;

public interface TransactionService {
    Transaction transfer(Transaction transaction);

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findTransactionsByAccountNumberAndTime(String accountNumber, String from, String to);

    List<Transaction> findAllByTransactionDateBetween(String from, String to);

    List<Report> findAllByTransactionDateBetweenAndStatus(String from, String to, Integer status);

    List<Object[]> reportByDay(String dateTime);
}
