package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.app.AboutUs;
import com.saad.baitalkhairat.model.app.AppBank;
import com.saad.baitalkhairat.model.app.ContactUs;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.quastion.QuestionResponse;
import com.saad.baitalkhairat.model.slider.SliderResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNoStandard;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponse;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.GET;


public class AppService {

    private static AppService instance;
    private static int timeOutDB = 10;
    Context mContext;
    private DataApi mDataApi;

    private AppService() {
        mDataApi = ApiClient.getRetrofitClient(ApiConstants.BASE_URL).create(DataApi.class);
    }

    public static AppService getInstance() {
        if (instance == null) {
            instance = new AppService();
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

    public void getSlider(Context mContext, boolean withProgress, APICallBack<SliderResponse> apiCallBack) {
        getDataApi().sliders()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<SliderResponse>(mContext, withProgress, apiCallBack));
    }

    public void getQuestions(Context mContext, boolean withProgress, APICallBack<QuestionResponse> apiCallBack) {
        getDataApi().questions()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<QuestionResponse>(mContext, withProgress, apiCallBack));
    }

    public void getAboutUs(Context mContext, boolean withProgress, APICallBack<AboutUs> apiCallBack) {
        getDataApi().about_us()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<AboutUs>(mContext, withProgress, apiCallBack));
    }

    public void getContactUs(Context mContext, boolean withProgress, APICallBack<ContactUs> apiCallBack) {
        getDataApi().contact_us()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<ContactUs>(mContext, withProgress, apiCallBack));
    }

    public void getAppBankInfo(Context mContext, boolean withProgress, APICallBack<AppBank> apiCallBack) {
        getDataApi().getAppBankInfo()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<AppBank>(mContext, withProgress, apiCallBack));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public void reInit() {
        instance = new AppService();
    }

    public interface DataApi {

        @GET(ApiConstants.apiAppService.COUNTRY_NAME)
        Single<Response<CountryCodeResponse>> countryName();

        @GET(ApiConstants.apiAppService.COUNTRY_CODE)
        Single<Response<CountryCodeResponse>> countryCode();

        @GET(ApiConstants.apiAppService.GENDERS)
        Single<Response<CountryCodeResponse>> genders();

        @GET(ApiConstants.apiAppService.SLIDERS)
        Single<Response<SliderResponse>> sliders();

        @GET(ApiConstants.apiAppService.QUESTION)
        Single<Response<QuestionResponse>> questions();

        @GET(ApiConstants.apiAppService.ABOUT_US)
        Single<Response<GeneralResponse<AboutUs>>> about_us();

        @GET(ApiConstants.apiAppService.CONTACT_US)
        Single<Response<GeneralResponse<ContactUs>>> contact_us();

        @GET(ApiConstants.apiAppService.APP_BANK_INFO)
        Single<Response<GeneralResponse<AppBank>>> getAppBankInfo();


    }
}


