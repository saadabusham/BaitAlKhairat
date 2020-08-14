package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.wallet.Wallet;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponse;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.GET;


public class WalletService {

    private static WalletService instance;
    private static int timeOutDB = 10;
    Context mContext;
    private DataApi mDataApi;

    private WalletService() {
        mDataApi = ApiClient.getRetrofitClient(ApiConstants.BASE_URL).create(DataApi.class);
    }

    public static WalletService getInstance() {
        if (instance == null) {
            instance = new WalletService();
        }
        return instance;
    }

    public void getWallet(Context mContext, APICallBack<Wallet> apiCallBack) {
        getDataApi().getWallet()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<Wallet>(mContext, true, apiCallBack));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {

        @GET(ApiConstants.apiWalletService.WALLET)
        Single<Response<GeneralResponse<Wallet>>> getWallet();

    }
}


