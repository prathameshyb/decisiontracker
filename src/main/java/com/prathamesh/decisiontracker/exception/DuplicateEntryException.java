package com.prathamesh.decisiontracker.exception;

public class DuplicateEntryException extends BaseException{
    public DuplicateEntryException (String message){
        super(message, 409);
    }
}
