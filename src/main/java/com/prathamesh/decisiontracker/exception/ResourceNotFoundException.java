package com.prathamesh.decisiontracker.exception;

public class ResourceNotFoundException extends BaseException{
    public ResourceNotFoundException(String resource, int id){
        super(resource + " not found with id " + id, 404);
    }
}
