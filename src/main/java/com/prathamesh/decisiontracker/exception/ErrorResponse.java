package com.prathamesh.decisiontracker.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timeStamp;

    public ErrorResponse(int status, String message, String path, LocalDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
