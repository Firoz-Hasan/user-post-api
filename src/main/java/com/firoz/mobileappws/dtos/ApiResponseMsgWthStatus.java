package com.firoz.mobileappws.dtos;

public class ApiResponseMsgWthStatus {

    private int status;
    private String message;

    public ApiResponseMsgWthStatus(int status, String message) {
        this.status = status;
        this.message = message;
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

}
