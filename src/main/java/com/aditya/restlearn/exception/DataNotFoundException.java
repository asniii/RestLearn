package com.aditya.restlearn.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8666958031832972395L;

    public DataNotFoundException(String message){
        super(message);
    }
}
