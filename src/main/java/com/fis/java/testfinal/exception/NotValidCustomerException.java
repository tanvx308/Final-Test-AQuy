package com.fis.java.testfinal.exception;

public class NotValidCustomerException extends AppException{
    public NotValidCustomerException(String code, String message){
        super(code, message);
    }
}
