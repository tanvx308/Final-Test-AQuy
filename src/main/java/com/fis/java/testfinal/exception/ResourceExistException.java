package com.fis.java.testfinal.exception;

import lombok.Data;

@Data
public class ResourceExistException extends AppException {
    public ResourceExistException(String code, String resourceName, String fieldName, String fieldValue){
        super(code, resourceName, fieldName, fieldValue);
    }
}
