package com.saad.baitalkhairat.repository.network.ApiCallHandler;

import android.content.Context;

import com.google.gson.Gson;
import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.ui.dialog.CustomDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.Response;

public class CustomObserverResponseNew<T, E> extends CustomDialogUtils implements Observer<Response<GeneralResponseNew<T, E>>> {

    APICallBackNew apiCallBack;
    Context context;
    boolean withProgress;
    public CustomObserverResponseNew(Context mContext, boolean withProgress, APICallBackNew apiCallBack) {
        super(mContext, withProgress, false);
        context = mContext;
        this.apiCallBack = apiCallBack;
        this.withProgress = withProgress;
    }
//    public CustomObserverResponse(APICallBack apiCallBack) {
//        this.apiCallBack = apiCallBack;
//    }

    @Override
    public void onSubscribe(Disposable d) {
        if (withProgress) {
            showProgress();
        }
    }

    @Override
    public void onNext(Response<GeneralResponseNew<T, E>> generalResponseResponse) {

        if (generalResponseResponse.code() == 200 && generalResponseResponse.body().getError() == null) {
            this.apiCallBack.onSuccess(generalResponseResponse.body().getData());
        } else {
            int code = generalResponseResponse.code();
            GeneralResponseNew errorBody = null;
            if (generalResponseResponse.errorBody() != null) {
                try {
                    JSONObject jsonObject = new JSONObject(generalResponseResponse.errorBody().string());
                    errorBody = new Gson().fromJson(jsonObject.toString(), GeneralResponseNew.class);
                    errorHandler(code, errorBody, jsonObject.has("errors") ? jsonObject.getString("errors") : "");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

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
            errorHandler(statusCode, null, "");
        } else if (e instanceof SocketTimeoutException) {
            // handle timeout from Retrofit
            this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                    .getResources().getString(R.string.no_internet_connection), 0);
        } else if (e instanceof IOException) {
            // file was not found, do something
            this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                    .getResources().getString(R.string.no_internet_connection), 0);
        }
    }

    @Override
    public void onComplete() {
        if (withProgress) {
            hideProgress();
        }
    }

    private void errorHandler(int code, GeneralResponseNew errorBody, String jsonError) {
        switch (code) {
            case 401:
            case 403:
                if (errorBody != null &&
                        errorBody.getMessage() != null) {
                    this.apiCallBack.onError(jsonError, code);
                } else
                    this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                            .getResources().getString(R.string.unauthorized), code);
                break;
            case 404:
                if (errorBody != null &&
                        errorBody.getError() != null) {
                    this.apiCallBack.onError(jsonError, code);
                } else
                    this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                            .getResources().getString(R.string.not_found), code);
                break;
            case 405:
                this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.error_request_type), code);
                break;
            case 413:
                this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.file_too_large), code);
                break;
            case 500:
                this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                        .getResources().getString(R.string.server_error), code);
                break;
            case 422:
                if (errorBody != null &&
                        errorBody.getError() != null) {
                    this.apiCallBack.onError(jsonError, code);
                } else
                    this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                            .getResources().getString(R.string.error_parsing_entity), code);
                break;
            default:
                if (errorBody != null &&
                        errorBody.getError() != null) {
                    this.apiCallBack.onError(jsonError, code);
                } else {
                    this.apiCallBack.onNetworkError(App.getInstance().getApplicationContext()
                            .getResources().getString(R.string.server_error), code);
                }
        }
    }


}
