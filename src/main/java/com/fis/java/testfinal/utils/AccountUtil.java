package com.fis.java.testfinal.utils;

import com.fis.java.testfinal.constant.AccountStatus;
import com.fis.java.testfinal.dto.FormAccountDto;
import com.fis.java.testfinal.entity.Account;

import java.time.LocalDateTime;

public class AccountUtil {
    public static Account convertFromDto(FormAccountDto dto){
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        account.setStatus(AccountStatus.APPROVE);
        account.setCreateDateTime(LocalDateTime.now());
        account.setUpdateDateTime(LocalDateTime.now());
        account.setCustomer(dto.getCustomer());
        return account;
    }
}
