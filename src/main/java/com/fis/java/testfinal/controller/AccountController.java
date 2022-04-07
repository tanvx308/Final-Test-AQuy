package com.fis.java.testfinal.controller;

import com.fis.java.testfinal.constant.AccountStatus;
import com.fis.java.testfinal.dto.FormAccountDto;
import com.fis.java.testfinal.entity.Account;
import com.fis.java.testfinal.service.AccountService;
import com.fis.java.testfinal.utils.AccountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountController {
    @Autowired
    AccountService accountService;

    //api Liệt kê danh sách Account
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccountsOrderByCustomerName(@RequestParam("page")Optional<Integer> page,
                                                            @RequestParam("size")Optional<Integer> size){
        PageRequest pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(6));
        List<Account> accounts = accountService.findAccountsOrderByCustomerName(pageable);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    //api Tìm kiếm Account
    //hiệu lực theo mã
    //khách hàng
    @GetMapping("/account/customer/{id}/active")
    public ResponseEntity<List<Account>> getActiveAccountByCustomerIdOrderOrderByAccountNumber(@PathVariable("id") Optional<Long> id){
        Integer status = AccountStatus.ACTIVE;
        List<Account> accounts = accountService.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id.orElse(null), status);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }


    //api Tìm kiếm Account hết
    //hiệu lực theo mã
    //khách hàng
    @GetMapping("/account/customer/{id}/in-active")
    public ResponseEntity<List<Account>> getInActiveAccountByCustomerIdOrderOrderByAccountNumber(@PathVariable("id") Optional<Long> id){
        Integer status = AccountStatus.IN_ACTIVE;
        List<Account> accounts = accountService.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id.orElse(null), status);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    //api Tìm kiếm tất cả
    //Account theo khách
    //hàng
    @GetMapping("/account/customer/{id}")
    public ResponseEntity<List<Account>> getAccountByCustomerIdOrderOrderByStatusAndAccountNumber(@PathVariable("id") Optional<Long> id){
        Integer status = null;
        List<Account> accounts = accountService.findAccountByCustomer_IdAndStatusOrderByAccountNumber(id.orElse(null), status);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    //api Tìm kiếm account
    //theo Id
    @GetMapping("/account/id/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Optional<Long> id){
        return new ResponseEntity<>(accountService.findAccountById(id.orElse(null)), HttpStatus.OK);
    }

    //api Tìm kiếm account
    //theo số TK
    @GetMapping("/account/account-number/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable("accountNumber") Optional<String> accountNumber){
        return new ResponseEntity<>(accountService.findAccountByAccountNumber(accountNumber.orElse(null)), HttpStatus.OK);
    }

    //api Thêm mới tài khoản
    @PostMapping("/account/save")
    public ResponseEntity<Account> createAccount(@RequestBody FormAccountDto dto){
        Account account = AccountUtil.convertFromDto(dto);
        account = accountService.saveAccount(account);
        log.info("Account with id {} created.", account.getId());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    //api Phê duyệt tài khoản
    @GetMapping("/account/approve/{id}")
    public ResponseEntity<Account> approveAccount(@PathVariable("id") Optional<Long> id){
        Account account = accountService.approveAccount(id.orElse(null));
        log.info("Account with id {} approved.", id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    //api Cập nhật trạng thái
    //tài khoản (Hết hiệu
    //lực/ tạm khóa)
    @GetMapping("/account/{id}/status/{status}")
    public ResponseEntity<Account> updateAccountStatus(@PathVariable("id") Optional<Long> id,
                                                 @PathVariable("status")Optional<Integer> status){
        Account account = accountService.updateAccountStatus(id.orElse(null), status.orElse(null));
        log.info("Account with id {} update new status: {}", id, status);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
