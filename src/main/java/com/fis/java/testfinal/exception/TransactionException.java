package com.fis.java.testfinal.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionException extends RuntimeException {
   private Integer code;
    public TransactionException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
