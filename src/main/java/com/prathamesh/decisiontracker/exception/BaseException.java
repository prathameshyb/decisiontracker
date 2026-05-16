package com.prathamesh.decisiontracker.exception;

public class BaseException extends RuntimeException{
    private final int statusCode;

    public BaseException(String message, int statusCode){
        this.statusCode = statusCode;
        super(message);
    }
    public int getStatusCode(){
        return statusCode;
    }
}
