package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.wallet.CheckCharge;
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
import retrofit2.http.Query;


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

    public void checkCharge(Context mContext, int type, double amount, APICallBack<CheckCharge> apiCallBack) {
        getDataApi().checkCharge(amount, type)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<CheckCharge>(mContext, true, apiCallBack));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {

        @GET(ApiConstants.apiWalletService.WALLET)
        Single<Response<GeneralResponse<Wallet>>> getWallet();

        @GET(ApiConstants.apiWalletService.WALLET)
        Single<Response<GeneralResponse<CheckCharge>>> checkCharge(@Query("amount") double amount,
                                                                   @Query("charge_type") int charge_type);

    }
}


