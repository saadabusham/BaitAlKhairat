package com.saad.baitalkhairat.repository.network.ApiCallHandler;

public class GeneralResponse<T> {

    private String message = "";
    private boolean error;
    private T data;

    public String getMessage() {
        return message;
    }

    public boolean getError() {
        return error;
    }

    public T getData() {
        return data;
    }
}
