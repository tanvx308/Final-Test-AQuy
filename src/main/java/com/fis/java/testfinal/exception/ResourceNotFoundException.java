package com.fis.java.testfinal.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String code, String resourceName, String fieldName, String fieldValue){
        super(code, resourceName, fieldName, fieldValue);
    }
}
