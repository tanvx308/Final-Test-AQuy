package com.fis.java.testfinal.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class AppException extends RuntimeException{
    private String code;
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public AppException(String code, String resourceName, String fieldName, String fieldValue){
        super(String.format("Error %s: %s with %s : %s", code, resourceName, fieldName, fieldValue));
        this.code = code;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
