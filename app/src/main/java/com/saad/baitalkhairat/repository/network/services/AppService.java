package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.model.app.AppBank;
import com.saad.baitalkhairat.model.app.ContactUs;
import com.saad.baitalkhairat.model.app.aboutus.AboutUs;
import com.saad.baitalkhairat.model.app.aboutus.AboutUsSectionsResponse;
import com.saad.baitalkhairat.model.app.aboutus.BaitAlKhairatResources;
import com.saad.baitalkhairat.model.app.aboutus.FundingResourceResponse;
import com.saad.baitalkhairat.model.app.aboutus.HumanityValueResponse;
import com.saad.baitalkhairat.model.app.privacypolicy.PrivacyPolicyResponse;
import com.saad.baitalkhairat.model.app.termsofuse.TermsOfUseResponse;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.news.NewsResponse;
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
import retrofit2.http.Query;


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

    public void getMaritals(Context mContext, boolean withProgress, APICallBack<CountryCodeResponse> apiCallBack) {
        getDataApi().maritals()
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

    public void getQuestions(Context mContext, boolean withProgress, int page, APICallBack<QuestionResponse> apiCallBack) {
        getDataApi().questions(page)
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

    public void getAboutUsSections(Context mContext, boolean withProgress, APICallBack<AboutUsSectionsResponse> apiCallBack) {
        getDataApi().about_us_sections()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<AboutUsSectionsResponse>(mContext, withProgress, apiCallBack));
    }

    public void getFundingResource(Context mContext, boolean withProgress, APICallBack<FundingResourceResponse> apiCallBack) {
        getDataApi().funding_resource()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<FundingResourceResponse>(mContext, withProgress, apiCallBack));
    }

    public void getBaitResource(Context mContext, boolean withProgress, APICallBack<BaitAlKhairatResources> apiCallBack) {
        getDataApi().bait_resource()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<BaitAlKhairatResources>(mContext, withProgress, apiCallBack));
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

    public void getCaseType(Context mContext, boolean withProgress, APICallBack<CountryCodeResponse> apiCallBack) {
        getDataApi().caseType()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CountryCodeResponse>(mContext, withProgress, apiCallBack));
    }

    public void getDegree(Context mContext, boolean withProgress, APICallBack<CountryCodeResponse> apiCallBack) {
        getDataApi().degree()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CountryCodeResponse>(mContext, withProgress, apiCallBack));
    }

    public void getHumanityValues(Context mContext, boolean withProgress, APICallBack<HumanityValueResponse> apiCallBack) {
        getDataApi().humanityValues()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<HumanityValueResponse>(mContext, withProgress, apiCallBack));
    }


    public void getTermsOfUse(Context mContext, boolean withProgress, int page, APICallBack<TermsOfUseResponse> apiCallBack) {
        getDataApi().termsOfUse(page)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<TermsOfUseResponse>(mContext, withProgress, apiCallBack));
    }

    public void getNews(Context mContext, boolean withProgress, int page, APICallBack<NewsResponse> apiCallBack) {
        getDataApi().news(page)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<NewsResponse>(mContext, withProgress, apiCallBack));
    }

    public void getPrivacyPolicy(Context mContext, boolean withProgress, int page, APICallBack<PrivacyPolicyResponse> apiCallBack) {
        getDataApi().privacyPolicy(page)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<PrivacyPolicyResponse>(mContext, withProgress, apiCallBack));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public void reInit() {
        instance = new AppService();
    }

    public interface DataApi {

        @GET(ApiConstants.apiAppService.DEGREE)
        Single<Response<CountryCodeResponse>> degree();

        @GET(ApiConstants.apiAppService.CASE_TYPE)
        Single<Response<CountryCodeResponse>> caseType();

        @GET(ApiConstants.apiAppService.COUNTRY_NAME)
        Single<Response<CountryCodeResponse>> countryName();

        @GET(ApiConstants.apiAppService.COUNTRY_CODE)
        Single<Response<CountryCodeResponse>> countryCode();

        @GET(ApiConstants.apiAppService.GENDERS)
        Single<Response<CountryCodeResponse>> genders();

        @GET(ApiConstants.apiAppService.MARITALS)
        Single<Response<CountryCodeResponse>> maritals();

        @GET(ApiConstants.apiAppService.SLIDERS)
        Single<Response<SliderResponse>> sliders();

        @GET(ApiConstants.apiAppService.QUESTION)
        Single<Response<QuestionResponse>> questions(@Query("page") int page);

        @GET(ApiConstants.apiAppService.ABOUT_US)
        Single<Response<GeneralResponse<AboutUs>>> about_us();

        @GET(ApiConstants.apiAppService.ABOUT_US_SECTIONS)
        Single<Response<AboutUsSectionsResponse>> about_us_sections();

        @GET(ApiConstants.apiAppService.FUNDING_RESOURCE)
        Single<Response<FundingResourceResponse>> funding_resource();

        @GET(ApiConstants.apiAppService.BAIT_RESOURCE)
        Single<Response<GeneralResponse<BaitAlKhairatResources>>> bait_resource();

        @GET(ApiConstants.apiAppService.CONTACT_US)
        Single<Response<GeneralResponse<ContactUs>>> contact_us();

        @GET(ApiConstants.apiAppService.APP_BANK_INFO)
        Single<Response<GeneralResponse<AppBank>>> getAppBankInfo();

        @GET(ApiConstants.apiAppService.HUMANITY_VALUES)
        Single<Response<HumanityValueResponse>> humanityValues();

        @GET(ApiConstants.apiAppService.TERMS_OF_USE)
        Single<Response<TermsOfUseResponse>> termsOfUse(@Query("page") int page);

        @GET(ApiConstants.apiAppService.NEWS)
        Single<Response<NewsResponse>> news(@Query("page") int page);

        @GET(ApiConstants.apiAppService.PRIVACY_POLICY)
        Single<Response<PrivacyPolicyResponse>> privacyPolicy(@Query("page") int page);
    }
}


