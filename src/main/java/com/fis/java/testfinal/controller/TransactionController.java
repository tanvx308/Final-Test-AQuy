package com.fis.java.testfinal.controller;

import com.fis.java.testfinal.constant.TransactionStatus;
import com.fis.java.testfinal.dto.FormTransactionDto;
import com.fis.java.testfinal.entity.Transaction;
import com.fis.java.testfinal.exception.TransactionException;
import com.fis.java.testfinal.service.TransactionService;
import com.fis.java.testfinal.utils.TransactionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/transfer")
    @Async
    public ResponseEntity<?> createTransaction(@RequestBody FormTransactionDto dto){
        Transaction transaction = TransactionUtil.convertFromDto(dto);
        try {
            transaction = transactionService.transfer(transaction);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }catch (TransactionException e){
            System.out.println(e.toString());
            transaction.setStatus(e.getCode());
            transaction.setErrorReason(e.getMessage());
            log.error(e.getMessage());
            transaction = transactionService.saveTransaction(transaction);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
    }

    @PostMapping("/transactions/account/{accountNumber}")
    public ResponseEntity<?> getTransactionsByAccountNumberAndTime(@PathVariable("accountNumber") String accountNumber,
                                                                   @RequestParam("fromDate")String from,
                                                                   @RequestParam("toDate") String to){
        return new ResponseEntity<>(transactionService.findTransactionsByAccountNumberAndTime(accountNumber, from, to), HttpStatus.OK);
    }

    @PostMapping("/transactions/report-detail")
    public ResponseEntity<?> getAllByTransactionDateBetween( @RequestParam("fromDate")String from,
                                                             @RequestParam("toDate") String to){
        return new ResponseEntity<>(transactionService.findAllByTransactionDateBetween(from, to), HttpStatus.OK);
    }

    @PostMapping("/transactions/report-statistical")
    public ResponseEntity<?> getAllByTransactionDateBetweenAndStatus( @RequestParam("fromDate")String from,
                                                             @RequestParam("toDate") String to,
                                                                      @RequestParam("status") Integer status){
        return new ResponseEntity<>(transactionService.findAllByTransactionDateBetweenAndStatus(from, to, status), HttpStatus.OK);
    }
}
