package com.saad.baitalkhairat.repository.network.ApiCallHandler;

import android.content.Context;

import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.ui.dialog.CustomDialogUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.Response;

public class CustomObserverResponseNoStandard<T> extends CustomDialogUtils implements Observer<Response<T>> {

    APICallBack apiCallBack;
    Context context;
    boolean withProgress;
    public CustomObserverResponseNoStandard(Context mContext, boolean withProgress, APICallBack apiCallBack) {
        super(mContext, withProgress, false);
        context = mContext;
        this.apiCallBack = apiCallBack;
        this.withProgress = withProgress;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (withProgress) {
            showProgress();
        }
    }

    @Override
    public void onNext(Response<T> generalResponseResponse) {

        if (generalResponseResponse.code() == 200) {
            this.apiCallBack.onSuccess(generalResponseResponse.body());
        } else {
            int code = generalResponseResponse.code();
            errorHandler(code);
        }
    }


    @Override
    public void onError(Throwable e) {
        if (withProgress) {
            hideProgress();
        }
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int statusCode = httpException.code();
            // handle different HTTP error codes here (4xx)
            errorHandler(statusCode);
        } else if (e instanceof SocketTimeoutException) {
            // handle timeout from Retrofit
            this.apiCallBack.onError(App.getInstance().getApplicationContext()
                    .getResources().getString(R.string.no_internet_connection), 0);
        } else if (e instanceof IOException) {
            // file was not found, do something
            this.apiCallBack.onError(App.getInstance().getApplicationContext()
                    .getResources().getString(R.string.no_internet_connection), 0);
        }
    }

    @Override
    public void onComplete() {
        if (withProgress) {
            hideProgress();
        }
    }

    private void errorHandler(int code) {
        switch (code) {
            case 401:
            case 403:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.unauthorized), code);
                break;
            case 404:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.not_found), code);
                break;
            case 405:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.error_request_type), code);
                break;
            case 413:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.file_too_large), code);
                break;
            case 500:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.server_error), code);
                break;
            case 422:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.error_parsing_entity), code);
                break;
            default:
                this.apiCallBack.onError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.server_error), code);
        }
    }


}
