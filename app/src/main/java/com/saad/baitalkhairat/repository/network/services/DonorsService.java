package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.Filter;
import com.saad.baitalkhairat.model.donors.CasesResponse;
import com.saad.baitalkhairat.model.donors.CategoryResponse;
import com.saad.baitalkhairat.model.errormodel.AddToCartError;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBackNew;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNew;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNoStandard;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponseNew;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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

    public void getCategory(Context mContext, boolean withProgress, APICallBack<CategoryResponse> apiCallBack) {
        getDataApi().categories()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CategoryResponse>(mContext, withProgress, apiCallBack));
    }

    public void getCases(Context mContext, boolean withProgress, Filter filter, int page, APICallBack<CasesResponse> apiCallBack) {
        getDataApi().cases(filter.getType() != 0 ? filter.getType() : null
                , !filter.getGender().isEmpty() ? filter.getGender() : null
                , !filter.getCountry().isEmpty() ? filter.getCountry() : null
                , page)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CasesResponse>(mContext, withProgress, apiCallBack));
    }

    public void addToCart(Context mContext, boolean withProgress, String UUID, int id, String donation_amount, APICallBackNew<Object> apiCallBack) {
        getDataApi().addToCart(UUID, id, donation_amount)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNew<Object, AddToCartError>(mContext, withProgress, apiCallBack));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {

        @GET(ApiConstants.apiDonorsService.NEEDS_CATEGORIES)
        Single<Response<CategoryResponse>> categories();

        @GET(ApiConstants.apiDonorsService.CATEGORY_CASES)
        Single<Response<CasesResponse>> cases(@Query("type") Integer type,
                                              @Query("gender") String gender,
                                              @Query("country") String country,
                                              @Query("page") int page);

        @POST(ApiConstants.apiDonorsService.ADD_TO_CART)
        Single<Response<GeneralResponseNew<Object, AddToCartError>>> addToCart(@Header("platform-id") String platform_id,
                                                                               @Query("id") int id,
                                                                               @Query("donation_amount") String donation_amount);
    }
}


