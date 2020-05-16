package com.saad.baitalkhairat.ui.auth.otpverifier;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentOtpVerifierBinding;
import com.saad.baitalkhairat.enums.PhoneNumberTypes;
import com.saad.baitalkhairat.model.VerifyPhoneResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.TimeUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OtpVerifierViewModel extends BaseViewModel<OtpVerifierNavigator, FragmentOtpVerifierBinding> {

    int type;
    long milliToFinish = 90000;
    String token = "";

    CountDownTimer countDownTimer = new CountDownTimer(milliToFinish, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getViewBinding().tvTimeToSecond.setText(TimeUtils.millisecondToMinAndSec(millisUntilFinished));
        }

        @Override
        public void onFinish() {
            milliToFinish = 0;
            getViewBinding().tvResend.setTextColor(getMyContext().getResources().getColor(R.color.green));
            getViewBinding().tvTimeToSecond.setTextColor(getMyContext().getResources().getColor(R.color.green));
        }
    };

    public <V extends ViewDataBinding, N extends BaseNavigator> OtpVerifierViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (OtpVerifierNavigator) navigation, (FragmentOtpVerifierBinding) viewDataBinding);
        setOtpTextWatcher();
        countDownTimer.start();
    }

    @Override
    protected void setUp() {
        token = getNavigator().getToken();
    }

    public void setType(int type) {
        this.type = type;
    }

    public void verifyCode() {
//        if (isValidate()) {
//            getDataManager().getAuthService().getDataApi().verifyCode(token, getOtp())
//                    .toObservable()
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new CustomObserverResponse<String>(getMyContext(), true, new APICallBack<String>() {
//                        @Override
//                        public void onSuccess(String response) {
//                            if (type == PhoneNumberTypes.REGISTER.getValue()) {
//
//                            } else if (type == PhoneNumberTypes.FORGET_PASSWORD.getValue()) {
//                                getMyContext().startActivity
//                                        (CreatePasswordActivity.newIntent(getMyContext(), token));
//                            }
//                        }
//
//                        @Override
//                        public void onError(String error, int errorCode) {
//                            showToast(error);
//                        }
//                    }));
//
////            }
//        }
        if (type == PhoneNumberTypes.FORGET_PASSWORD.getValue()) {
            Bundle data = new Bundle();
            data.putString("token", token);
            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.createPasswordFragment, data);
        }
    }
    public void resendCode() {
        if (milliToFinish == 0) {
            getDataManager().getAuthService().getDataApi().resendCode(token)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponse<VerifyPhoneResponse>(getMyContext(), true, new APICallBack<VerifyPhoneResponse>() {
                        @Override
                        public void onSuccess(VerifyPhoneResponse response) {
                            token = response.getToken();
                            milliToFinish = 90000;
                            countDownTimer.start();
                            getViewBinding().tvResend.setTextColor(getMyContext().getResources().getColor(R.color.black));
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            showToast(error);
                        }
                    }));
        }
    }

    public void onEditClick() {
        popUp();
    }

    public boolean isValidate() {
        int error = 0;
        if (getViewBinding().userOtp1.getText().toString().isEmpty()) {
            getViewBinding().userOtp1.setHintTextColor(getMyContext().getResources().getColor(R.color.red));
            error = +1;
        }
        if (getViewBinding().userOtp2.getText().toString().isEmpty()) {
            getViewBinding().userOtp2.setHintTextColor(getMyContext().getResources().getColor(R.color.red));
            error = +1;
        }
        if (getViewBinding().userOtp3.getText().toString().isEmpty()) {
            getViewBinding().userOtp3.setHintTextColor(getMyContext().getResources().getColor(R.color.red));
            error = +1;
        }
        if (getViewBinding().userOtp4.getText().toString().isEmpty()) {
            getViewBinding().userOtp4.setHintTextColor(getMyContext().getResources().getColor(R.color.red));
            error = +1;
        }
        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);

        return error == 0;
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnVerifyCode.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnVerifyCode.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnVerifyCode.setEnabled(true);
        } else {
            getViewBinding().btnVerifyCode.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnVerifyCode.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnVerifyCode.setEnabled(false);
        }
    }

    public String getOtp() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getViewBinding().userOtp1.getText().toString());
        stringBuilder.append(getViewBinding().userOtp2.getText().toString());
        stringBuilder.append(getViewBinding().userOtp3.getText().toString());
        stringBuilder.append(getViewBinding().userOtp4.getText().toString());
        return stringBuilder.toString();
    }

    public void setOtpTextWatcher() {
        getViewBinding().userOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 1) {
                    getViewBinding().userOtp2.requestFocus();
                }
                isValidate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewBinding().userOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 1) {
                    getViewBinding().userOtp3.requestFocus();
                }
                isValidate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewBinding().userOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 1) {
                    getViewBinding().userOtp4.requestFocus();
                }
                isValidate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewBinding().userOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}