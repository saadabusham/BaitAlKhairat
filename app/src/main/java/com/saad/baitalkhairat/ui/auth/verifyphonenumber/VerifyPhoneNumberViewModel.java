package com.saad.baitalkhairat.ui.auth.verifyphonenumber;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentVerifyPhoneNumberBinding;
import com.saad.baitalkhairat.enums.PhoneNumberTypes;
import com.saad.baitalkhairat.model.VerifyPhoneResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VerifyPhoneNumberViewModel extends BaseViewModel<VerifyPhoneNumberNavigator, FragmentVerifyPhoneNumberBinding> {


    public <V extends ViewDataBinding, N extends BaseNavigator> VerifyPhoneNumberViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (VerifyPhoneNumberNavigator) navigation, (FragmentVerifyPhoneNumberBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        setUpSpinnerCountry();
        setUpWatcher();
    }

    private void setUpWatcher() {
        getViewBinding().edPhoneNumber.addTextChangedListener(new TextWatcher() {
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

    public void sendCode() {
//        checkPhoneForForgetPassword();
        Bundle data = new Bundle();
        data.putInt("type", PhoneNumberTypes.FORGET_PASSWORD.getValue());
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.otpVerifierFragment, data);
    }

    private void setUpSpinnerCountry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.jordan_code));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountryCode.setAdapter(adapter);
    }

    public void checkPhoneForForgetPassword() {
        getDataManager().getAuthService().getDataApi().forgetPassword(getViewBinding().edPhoneNumber.getText().toString().trim())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<VerifyPhoneResponse>(getMyContext(), true, new APICallBack<VerifyPhoneResponse>() {
                    @Override
                    public void onSuccess(VerifyPhoneResponse response) {

                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showToast(error);
                    }
                }));
    }

    private boolean isValidate() {
        int error = 0;
        if (getViewBinding().edPhoneNumber.getText().toString().trim().isEmpty()) {
            getViewBinding().edPhoneNumber.setError(getMyContext().getString(R.string.please_fill_your_phone_number));
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
            getViewBinding().btnSendCode.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnSendCode.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnSendCode.setEnabled(true);
        } else {
            getViewBinding().btnSendCode.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnSendCode.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnSendCode.setEnabled(false);
        }
    }
}
