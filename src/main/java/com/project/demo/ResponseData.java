package com.project.demo;

public class ResponseData {
    private Object data;
    private Object error;
    private String status;
    private Object message;
    private Integer statusCode;

    public ResponseData(Object data, Integer statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public ResponseData(Object data, String status, Integer statusCode) {
        this.data = data;
        this.status = status;
        this.statusCode = statusCode;
    }

    public ResponseData(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
