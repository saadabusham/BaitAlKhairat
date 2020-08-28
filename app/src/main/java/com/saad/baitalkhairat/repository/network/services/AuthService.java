package com.saad.baitalkhairat.repository.network.services;

import android.content.Context;

import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.LoginObject;
import com.saad.baitalkhairat.model.ProfileResponse;
import com.saad.baitalkhairat.model.RegisterResponse;
import com.saad.baitalkhairat.model.TokenResponse;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.model.VerifyPhoneResponeResponse;
import com.saad.baitalkhairat.model.errormodel.RegisterError;
import com.saad.baitalkhairat.model.errormodel.VerifyPhoneError;
import com.saad.baitalkhairat.model.needs.AddNeedDocResponse;
import com.saad.baitalkhairat.model.user.UserResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.ApiClient;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponse;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.GeneralResponseNew;
import com.saad.baitalkhairat.repository.network.ApiConstants;

import java.util.Calendar;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    public void getProfile(Context mContext, boolean withProgress, APICallBack<UserResponse> apiCallBack) {
        getDataApi().getProfile()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<UserResponse>(mContext, withProgress, apiCallBack));
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

    public void removeDocument(Context mContext, boolean withProgress, int docId, String binding_key, APICallBack<Object> apiCallBack) {
        getDataApi().removeDocument(docId, binding_key)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<Object>(mContext, withProgress, new APICallBack<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        apiCallBack.onSuccess(response);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        apiCallBack.onError(error, errorCode);
                    }
                }));
    }

    public void refreshToken(Context mContext, String refreshToken) {
        getDataApi().refreshToken(
                refreshToken, ApiConstants.PASSPORT_CLIENT_ID, ApiConstants.PASSPORT_CLIENT_SECRET)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<TokenResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<TokenResponse> tokenResponseResponse) {
                        if (tokenResponseResponse.code() == 200 &&
                                tokenResponseResponse.body() != null
                                && tokenResponseResponse.body().getLoginError() == null) {
                            tokenResponseResponse.body().setToken_generated_date(Calendar.getInstance().getTimeInMillis());
                            User.getInstance().setTokenResponse(tokenResponseResponse.body());
                            SessionManager.createUserLoginSession();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public DataApi getDataApi() {
        return mDataApi;
    }

    public void reInit() {
        instance = new AuthService();
    }

    public interface DataApi {

        @POST(ApiConstants.apiAuthService.VERIFY_PHONE)
        Single<Response<VerifyPhoneResponeResponse>> verifyPhone(@Query("phone") String phone,
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
        Single<Response<TokenResponse>> loginUser(@Body LoginObject loginObject,
                                                  @Query("client_id") int client_id,
                                                  @Query("client_secret") String client_secret);

        @POST(ApiConstants.apiAuthService.LOGIN_SOCIAL)
        Single<Response<GeneralResponse<RegisterResponse>>> loginWithSocial(@Body LoginObject socialLogin);

        @POST(ApiConstants.apiAuthService.FORGET_PASSWORD)
        Single<Response<GeneralResponseNew<String, VerifyPhoneError>>>
        forgetPassword(@Query("phone") String phoneNumber,
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

        @POST(ApiConstants.apiAuthService.DELETE_ATTACHMENT)
        Single<Response<GeneralResponse<Object>>> removeDocument(@Path("id") int id,
                                                                 @Query("binding_key") String binding_key);

        @PATCH(ApiConstants.apiAuthService.UPDATE_PASSWORD)
        Single<Response<GeneralResponse<String>>> updatePassword(@Query("password") String password,
                                                                 @Query("password_confirmation") String password_confirmation,
                                                                 @Query("current_password") String current_password);

        @PUT(ApiConstants.apiAuthService.UPDATE_PROFILE)
        Single<Response<GeneralResponse<ProfileResponse>>> updateProfile(@Body UserResponse user);

        @Multipart
        @PUT(ApiConstants.apiAuthService.UPDATE_PROFILE_PICTURE)
        Single<Response<GeneralResponse<ProfileResponse>>> updateProfilePicture(@Part MultipartBody.Part image,
                                                                                @Query("binding_key") String uuid);

        @Multipart
        @POST(ApiConstants.apiAuthService.ADD_ATTACHMENT)
        Single<Response<GeneralResponse<AddNeedDocResponse>>> addAttachment(@Part MultipartBody.Part image,
                                                                            @Query("binding_key") String uuid);

        @POST(ApiConstants.apiAuthService.REFRESH_TOKEN)
        Single<Response<TokenResponse>> refreshToken(@Query("refresh_token") String refresh_token,
                                                     @Query("client_id") int client_id,
                                                     @Query("client_secret") String client_secret);

        @GET(ApiConstants.apiAuthService.MY_PROFILE)
        Single<Response<GeneralResponse<UserResponse>>> getProfile();

    }
}


