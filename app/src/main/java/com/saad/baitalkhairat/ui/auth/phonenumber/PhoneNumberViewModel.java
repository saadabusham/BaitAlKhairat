package com.saad.baitalkhairat.ui.auth.phonenumber;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityPhoneNumberBinding;
import com.saad.baitalkhairat.enums.PhoneNumberTypes;
import com.saad.baitalkhairat.model.VerifyPhoneResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.auth.otpverifier.OtpVerifierActivity;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PhoneNumberViewModel extends BaseViewModel<PhoneNumberNavigator, ActivityPhoneNumberBinding> {

    int type;

    public <V extends ViewDataBinding, N extends BaseNavigator> PhoneNumberViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (PhoneNumberNavigator) navigation, (ActivityPhoneNumberBinding) viewDataBinding);
        getViewBinding().ccp.setCountryForNameCode(DeviceUtils.getDeviceCountryCode(getMyContext()));

    }

    public void loginClicked() {
        ((PhoneNumberActivity) getMyContext()).finish();
    }

    public void sendCode() {
        if (isValidate()) {
            if (type == PhoneNumberTypes.REGISTER.getValue() || type == PhoneNumberTypes.REGISTER_SOCIAL.getValue()) {
                checkPhoneForRegister();
            } else if (type == PhoneNumberTypes.FORGET_PASSWORD.getValue()) {
                checkPhoneForForgetPassword();
            }
        }
    }

    public void checkPhoneForRegister() {
        getDataManager().getAuthService().getDataApi().verifyPhone(getViewBinding().ccp.getSelectedCountryCode() + getViewBinding().edPhoneNumber.getText().toString().trim())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<VerifyPhoneResponse>(getMyContext(), true, new APICallBack<VerifyPhoneResponse>() {
                    @Override
                    public void onSuccess(VerifyPhoneResponse response) {
                        getMyContext().startActivity(OtpVerifierActivity.getStartIntent(getMyContext(),
                                getViewBinding().ccp.getSelectedCountryCode() +
                                        getViewBinding().edPhoneNumber.getText().toString().trim(),
                                type, response.getToken()));
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showToast(error);
                    }
                }));
    }

    public void checkPhoneForForgetPassword() {
        getDataManager().getAuthService().getDataApi().forgetPassword(getViewBinding().ccp.getSelectedCountryCode() + getViewBinding().edPhoneNumber.getText().toString().trim())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<VerifyPhoneResponse>(getMyContext(), true, new APICallBack<VerifyPhoneResponse>() {
                    @Override
                    public void onSuccess(VerifyPhoneResponse response) {
                        getMyContext().startActivity(OtpVerifierActivity.getStartIntent(getMyContext(),
                                getViewBinding().ccp.getSelectedCountryCode() +
                                        getViewBinding().edPhoneNumber.getText().toString().trim(), type, response.getToken()));
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showToast(error);
                    }
                }));
    }

    private boolean isValidate() {
        if (getViewBinding().edPhoneNumber.getText().toString().trim().isEmpty()) {
            getViewBinding().edPhoneNumber.setError(getMyContext().getString(R.string.please_fill_your_phone_number));
            return false;
        }
        return true;
    }

    public void setType(int type) {
        this.type = type;
        if (type == PhoneNumberTypes.CHANGE_PHONE_NUMBER.getValue()) {
            getViewBinding().tvTitle.setText(getMyContext().getResources().getString(R.string.change_phone_number));
            getViewBinding().tvMessage.setText(getMyContext().getResources().getString(R.string.enter_your_new_phone_number_to_confirm));
        } else if (type == PhoneNumberTypes.FORGET_PASSWORD.getValue()) {
            getViewBinding().tvTitle.setText(getMyContext().getResources().getString(R.string.forget_password));
            getViewBinding().tvMessage.setText(getMyContext().getResources().getString(R.string.enter_your_phone_number_to_create_new_password));
        }
    }

    @Override
    protected void setUp() {

    }
}
