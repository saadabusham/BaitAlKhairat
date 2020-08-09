package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.LoginObject;
import com.saad.baitalkhairat.model.ProfileResponse;
import com.saad.baitalkhairat.model.RegisterResponse;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.model.errormodel.LoginError;
import com.saad.baitalkhairat.model.errormodel.RegisterError;
import com.saad.baitalkhairat.model.errormodel.VerifyPhoneError;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponseNew;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public class AuthService {

    private static AuthService instance;
    private static int timeOutDB = 10;
    Context mContext;
    private DataApi mDataApi;

    private AuthService() {
        mDataApi = ApiClient.getRetrofitClient(ApiConstants.BASE_URL).create(DataApi.class);
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }


    public void updateFirebaseToken(Context mContext, boolean withProgress, APICallBack apiCallBack) {
        getDataApi().updateFirebaseToken(
                SessionManager.getKeyFirebaseToken(),
                ApiConstants.PLATFORM)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<String>(mContext, withProgress, new APICallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        apiCallBack.onSuccess(response);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        apiCallBack.onError(error, errorCode);
                    }
                }));
    }

    public void logout(Context mContext, APICallBack apiCallBack) {
        getDataApi().logout(
                SessionManager.getKeyFirebaseToken())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<String>(mContext, true, new APICallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        apiCallBack.onSuccess(response);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        apiCallBack.onError(error, errorCode);
                    }
                }));
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public void reInit() {
        instance = new AuthService();
    }

    public interface DataApi {

        @POST(ApiConstants.apiAuthService.VERIFY_PHONE)
        Single<Response<GeneralResponseNew<String, VerifyPhoneError>>> verifyPhone(@Query("phone") String phone,
                                                                                   @Query("country_code") String country_code,
                                                                                   @Query("verificationCode") String verificationCode);

        @POST(ApiConstants.apiAuthService.VERIFY_CODE)
        Single<Response<GeneralResponseNew<User, RegisterError>>> verifyCode(@Body User user);

        @POST(ApiConstants.apiAuthService.RESEND_CODE)
        Single<Response<GeneralResponse<String>>> resendCode(@Query("phone") String phone,
                                                             @Query("country_code") String country_code);

        @POST(ApiConstants.apiAuthService.RESEND_CODE_TP_FORGET_PASSWORD)
        Single<Response<GeneralResponse<String>>> resendCodeToForgetPassword(@Query("phone") String phone,
                                                                             @Query("country_code") String country_code);

        @POST(ApiConstants.apiAuthService.REGISTER_USER)
        Single<Response<GeneralResponseNew<User, RegisterError>>> registerUser(@Body User user);

        @POST(ApiConstants.apiAuthService.LOGIN_USER)
        Single<Response<GeneralResponseNew<User, LoginError>>> loginUser(@Body LoginObject loginObject,
                                                                         @Query("client_id") int client_id,
                                                                         @Query("client_secret") String client_secret);

        @POST(ApiConstants.apiAuthService.LOGIN_SOCIAL)
        Single<Response<GeneralResponse<RegisterResponse>>> loginWithSocial(@Body LoginObject socialLogin);

        @POST(ApiConstants.apiAuthService.FORGET_PASSWORD)
        Single<Response<GeneralResponseNew<String, VerifyPhoneError>>>
        forgetPassword(@Query("phone_number") String phoneNumber,
                       @Query("country_code") String country_code);

        @POST(ApiConstants.apiAuthService.CREATE_PASSWORD)
        Single<Response<GeneralResponse<String>>> createPassword(@Query("mobile") String mobile,
                                                                 @Query("password") String password,
                                                                 @Query("password_confirmation") String phone_number,
                                                                 @Query("token") String token);

        @POST(ApiConstants.apiAuthService.UPDATE_FIREBASE_TOKEN)
        Single<Response<GeneralResponse<String>>> updateFirebaseToken(@Query("token") String token,
                                                                      @Query("platform") String platform);

        @POST(ApiConstants.apiAuthService.LOGOUT)
        Single<Response<GeneralResponse<String>>> logout(@Query("token") String token);

        @PATCH(ApiConstants.apiAuthService.UPDATE_PASSWORD)
        Single<Response<GeneralResponse<String>>> updatePassword(@Query("password") String password,
                                                                 @Query("password_confirmation") String password_confirmation,
                                                                 @Query("current_password") String current_password);

        @PATCH(ApiConstants.apiAuthService.UPDATE_PROFILE)
        Single<Response<GeneralResponse<ProfileResponse>>> updateProfile(@Query("email") String email,
                                                                         @Query("name") String name);

        @Multipart
        @POST(ApiConstants.apiAuthService.UPDATE_PROFILE_PICTURE)
        Single<Response<GeneralResponse<ProfileResponse>>> updateProfilePicture(@Part MultipartBody.Part image);

    }
}


