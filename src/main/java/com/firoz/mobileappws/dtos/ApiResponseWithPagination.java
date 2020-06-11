package com.firoz.mobileappws.dtos;

public class ApiResponseWithPagination {

    private int status;
    private String message;
    private Object common;
    private Object result;


    public Object getCommon() {
        return common;
    }

    public void setCommon(Object common) {
        this.common = common;
    }



    public ApiResponseWithPagination(int status, String message, Object common, Object result) {
        this.status = status;
        this.message = message;
        this.common = common;
        this.result = result;
    }

    public ApiResponseWithPagination(int status, String message) {
        this.status = status;
        this.message = message;
    }



    public ApiResponseWithPagination(int status, String message, Object result){
        this.status = status;
        this.message = message;
        this.result = result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
