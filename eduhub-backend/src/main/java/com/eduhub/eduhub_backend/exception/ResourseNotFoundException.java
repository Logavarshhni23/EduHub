package com.eduhub.eduhub_backend.exception;

public class ResourseNotFoundException extends RuntimeException{
    public ResourseNotFoundException(String resource, String field, String error){
        super(String.format("%s not found with %s:%s",resource, field, error));
    }
}
