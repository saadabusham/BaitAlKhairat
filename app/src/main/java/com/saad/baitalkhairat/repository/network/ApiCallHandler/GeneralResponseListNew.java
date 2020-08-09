package com.saad.baitalkhairat.repository.network.ApiCallHandler;

public class GeneralResponseListNew<T, E> {

    private String message = "";
    private T data;
    private E error;

    public String getMessage() {
        return message;
    }

    public E getError() {
        return error;
    }

    public T getData() {
        return data;
    }
}
