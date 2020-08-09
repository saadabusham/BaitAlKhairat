package com.saad.baitalkhairat.repository.network.ApiCallHandler;

public interface APICallBackNew<T> {

    void onSuccess(T response);

    void onError(String error, int errorCode);

    void onNetworkError(String error, int errorCode);
}
