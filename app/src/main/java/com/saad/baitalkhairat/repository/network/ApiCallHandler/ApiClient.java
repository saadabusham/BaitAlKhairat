package com.saad.baitalkhairat.repository.network.ApiCallHandler;


import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.model.auth.User;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String token = "";
    private static int timeOutDB = 10;

    public static Retrofit getRetrofitClient(String baseUrl) {
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request.Builder ongoing = chain.request().newBuilder();
            if (User.getInstance().getTokenResponse() != null) {
                ongoing.addHeader("Authorization", "Bearer " + User.getInstance().getTokenResponse().getAccessToken());
            } else {
                ongoing.addHeader("Authorization", "Bearer " + "");
            }
            ongoing.addHeader("Accept", "application/json");
            ongoing.addHeader("Accept-Language", LanguageUtils.getLanguage(App.getInstance().getApplicationContext()));

            return chain.proceed(ongoing.build());
        })
                .readTimeout(timeOutDB, TimeUnit.SECONDS)
                .connectTimeout(timeOutDB, TimeUnit.SECONDS)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient)
                .build();
        return mRetrofit;

    }
}
