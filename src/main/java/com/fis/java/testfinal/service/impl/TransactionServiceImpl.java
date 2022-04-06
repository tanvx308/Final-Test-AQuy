package com.fis.java.testfinal.service.impl;

import com.fis.java.testfinal.constant.AccountStatus;
import com.fis.java.testfinal.constant.TransactionStatus;
import com.fis.java.testfinal.entity.Account;
import com.fis.java.testfinal.entity.Transaction;
import com.fis.java.testfinal.exception.ArgumentNotValidException;
import com.fis.java.testfinal.exception.TransactionException;
import com.fis.java.testfinal.model.Report;
import com.fis.java.testfinal.repo.TransactionRepo;
import com.fis.java.testfinal.service.AccountService;
import com.fis.java.testfinal.service.TransactionService;
import com.fis.java.testfinal.utils.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    AccountService accountService;

    @Override
    @Transactional(rollbackOn = {TransactionException.class})
    public Transaction transfer(Transaction transaction) {
        Account from = accountService.findAccountById(transaction.getFromAccount().getId());
        Account to = accountService.findAccountById(transaction.getToAccount().getId());
        if (!from.getStatus().equals(AccountStatus.ACTIVE) || !accountService.existById(from.getId())) {
            throw new TransactionException(TransactionStatus.FAIL_BY_FROM_ACCOUNT, "From Account is not valid.");
        }
        if (!to.getStatus().equals(AccountStatus.ACTIVE) || !accountService.existById(to.getId())) {
            throw new TransactionException(TransactionStatus.FAIL_BY_TO_ACCOUNT, "To Account is not valid.");
        }
        if (from.getBalance() < transaction.getAmount()) {
            throw new TransactionException(TransactionStatus.FAIL_BY_BALANCE, "Balance is not enough.");
        }
        from.setBalance(from.getBalance() - transaction.getAmount());
        to.setBalance(to.getBalance() + transaction.getAmount());
        accountService.saveAccount(from);
        accountService.saveAccount(to);
        transaction.setStatus(TransactionStatus.SUCCESS);
        return transactionRepo.save(transaction);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> findTransactionsByAccountNumberAndTime(String accountNumber, String from, String to) {
        Account account = accountService.findAccountByAccountNumber(accountNumber);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return transactionRepo.findTransactionsByAccountNumberAndTime(account.getAccountNumber(),
                LocalDateTime.parse(from, formatter),
                LocalDateTime.parse(to, formatter));
    }

    @Override
    public List<Transaction> findAllByTransactionDateBetween(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
        LocalDateTime toDate = LocalDateTime.parse(to, formatter);
        Long daysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
        if(daysBetween > 60){
            throw new ArgumentNotValidException("REP400", "Days between must less than 60.");
        }
        return transactionRepo.findAllByTransactionDateBetween(fromDate, toDate);
    }

    @Override
    public List<Report> findAllByTransactionDateBetweenAndStatus(String from, String to, Integer status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
        LocalDateTime toDate = LocalDateTime.parse(to, formatter);
        return transactionRepo.reportByDayBetween(fromDate, toDate, status);
    }

    @Override
    public List<Object[]> reportByDay(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return transactionRepo.reportByDay(localDateTime);
    }
}
