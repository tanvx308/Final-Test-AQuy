package com.fis.java.testfinal.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArgumentNotValidException extends RuntimeException{
    String code;
    public ArgumentNotValidException(String code, String message){
        super(message);
        this.code = code;
    }
}
