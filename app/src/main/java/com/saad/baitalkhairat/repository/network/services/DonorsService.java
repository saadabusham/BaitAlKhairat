package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.donors.CasesResponse;
import com.saad.baitalkhairat.model.donors.CategoryResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNoStandard;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class DonorsService {

    private static DonorsService instance;
    private static int timeOutDB = 10;
    Context mContext;
    private DataApi mDataApi;

    private DonorsService() {
        mDataApi = ApiClient.getRetrofitClient(ApiConstants.BASE_URL).create(DataApi.class);
    }

    public static DonorsService getInstance() {
        if (instance == null) {
            instance = new DonorsService();
        }
        return instance;
    }


    public void getCountryName(Context mContext, boolean withProgress, APICallBack<CountryCodeResponse> apiCallBack) {
        getDataApi().countryName()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CountryCodeResponse>(mContext, withProgress, apiCallBack));
    }

    public void getCountryCode(Context mContext, boolean withProgress, APICallBack<CountryCodeResponse> apiCallBack) {
        getDataApi().countryCode()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CountryCodeResponse>(mContext, withProgress, apiCallBack));
    }

    public void getGenders(Context mContext, boolean withProgress, APICallBack<CountryCodeResponse> apiCallBack) {
        getDataApi().genders()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CountryCodeResponse>(mContext, withProgress, apiCallBack));
    }

    public void getCategory(Context mContext, boolean withProgress, APICallBack<CategoryResponse> apiCallBack) {
        getDataApi().categories()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CategoryResponse>(mContext, withProgress, apiCallBack));
    }

    public void getCases(Context mContext, boolean withProgress, int type, APICallBack<CasesResponse> apiCallBack) {
        getDataApi().cases(type)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CasesResponse>(mContext, withProgress, apiCallBack));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {

        @GET(ApiConstants.apiAppService.COUNTRY_NAME)
        Single<Response<CountryCodeResponse>> countryName();

        @GET(ApiConstants.apiAppService.COUNTRY_CODE)
        Single<Response<CountryCodeResponse>> countryCode();

        @GET(ApiConstants.apiAppService.GENDERS)
        Single<Response<CountryCodeResponse>> genders();

        @GET(ApiConstants.apiDonorsService.NEEDS_CATEGORIES)
        Single<Response<CategoryResponse>> categories();

        @GET(ApiConstants.apiDonorsService.CATEGORY_CASES)
        Single<Response<CasesResponse>> cases(@Query("type") int type);

    }
}


