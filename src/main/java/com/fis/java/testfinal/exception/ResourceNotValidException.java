package com.fis.java.testfinal.exception;

public class ResourceNotValidException extends AppException{
    public ResourceNotValidException(String code, String resourceName, String fieldName, String fieldValue){
        super(code, resourceName, fieldName, fieldValue);
    }
}
