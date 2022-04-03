package com.fis.java.testfinal.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends Exception{
    private String code;
    public AppException(String code, String message){
        super(message);
        this.code = code;
    }
}
