package com.saad.baitalkhairat.repository.network.services;


import com.saad.baitalkhairat.model.DataExample;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ErrorHandlingCallAdapter;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponse;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DataExampleService {

    private static DataExampleService instance;
    private static int timeOutDB = 10;
    private DataApi mDataApi;

    private DataExampleService() {
        mDataApi = ApiClient.getRetrofitClient(ApiConstants.BASE_URL).create(DataApi.class);
    }

    public static DataExampleService getInstance() {
        if (instance == null) {
            instance = new DataExampleService();
        }
        return instance;
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {
        @GET(ApiConstants.GET_DATA)
        ErrorHandlingCallAdapter.MyCall<GeneralResponse<ArrayList<DataExample>>> getAllData();

        @GET(ApiConstants.GET_DATA)
        Flowable<Response<GeneralResponse<List<DataExample>>>> getUser(@Query("lang") String lang,
                                                                       @Query("flag") int flag);

    }
}


