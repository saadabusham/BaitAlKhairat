package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.errormodel.AddNeedError;
import com.saad.baitalkhairat.model.needs.AddNeed;
import com.saad.baitalkhairat.model.needs.AddNeedDocResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBackNew;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNew;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponseNew;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public class NeedsService {

    private static NeedsService instance;
    private static int timeOutDB = 10;
    Context mContext;
    private DataApi mDataApi;

    private NeedsService() {
        mDataApi = ApiClient.getRetrofitClient(ApiConstants.BASE_URL).create(DataApi.class);
    }

    public static NeedsService getInstance() {
        if (instance == null) {
            instance = new NeedsService();
        }
        return instance;
    }


    public void addNeed(Context mContext, boolean withProgress, AddNeed addNeed, APICallBackNew<String> apiCallBack) {
        getDataApi().addNeed(addNeed)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNew<String, AddNeedError>(mContext, withProgress, apiCallBack));
    }

    public void addNeedDocs(Context mContext, boolean withProgress, MultipartBody.Part image, String bindingKey, APICallBack<AddNeedDocResponse> apiCallBack) {
        getDataApi().addNeedDocs(image, bindingKey)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<AddNeedDocResponse>(mContext, withProgress, apiCallBack));
    }


    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {

        @GET(ApiConstants.apiNeedsService.ADD_NEED)
        Single<Response<GeneralResponseNew<String, AddNeedError>>> addNeed(@Body AddNeed addNeed);

        @Multipart
        @POST(ApiConstants.apiNeedsService.ADD_NEED_DOCS)
        Single<Response<GeneralResponse<AddNeedDocResponse>>> addNeedDocs(@Part MultipartBody.Part image,
                                                                          @Query("binding_key") String bindingKey);
    }
}


