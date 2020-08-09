package com.saad.baitalkhairat.repository.network.ApiCallHandler;

public class GeneralResponseNew<T, E> {

    private String message = "";
    private T data;
    private E errors;

    public String getMessage() {
        return message;
    }

    public E getError() {
        return errors;
    }

    public T getData() {
        return data;
    }
}
