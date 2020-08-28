package com.saad.baitalkhairat.ui.auth.verifyphonenumber;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentVerifyPhoneNumberBinding;
import com.saad.baitalkhairat.enums.PhoneNumberTypes;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.errormodel.VerifyPhoneError;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBackNew;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNew;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VerifyPhoneNumberViewModel extends BaseViewModel<VerifyPhoneNumberNavigator, FragmentVerifyPhoneNumberBinding> {

    ArrayList<ListItem> countryCodeList = new ArrayList<>();
    ArrayAdapter<ListItem> countryCodeAdapter;

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
        if (isValidate())
            checkPhoneForForgetPassword();

    }

    private void setUpSpinnerCountry() {
        countryCodeAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, countryCodeList);
        countryCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountryCode.setAdapter(countryCodeAdapter);
        getCountryCodes();
    }

    private void getCountryCodes() {
        getDataManager().getAppService().getCountryCode(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                countryCodeAdapter.addAll(response.getList());
                countryCodeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    public void checkPhoneForForgetPassword() {
        getDataManager().getAuthService().getDataApi()
                .forgetPassword(getViewBinding().edPhoneNumber.getText().toString().trim(),
                        countryCodeAdapter.getItem(
                                getViewBinding().spinnerCountryCode
                                        .getSelectedItemPosition()).getValue())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNew<String, VerifyPhoneError>(getMyContext(),
                        true, new APICallBackNew<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Bundle data = new Bundle();
                        data.putInt("type", PhoneNumberTypes.FORGET_PASSWORD.getValue());
                        data.putString(AppConstants.BundleData.PHONE, getViewBinding().edPhoneNumber.getText().toString());
                        data.putString(AppConstants.BundleData.COUNTRY_CODE, countryCodeAdapter.getItem(
                                getViewBinding().spinnerCountryCode
                                        .getSelectedItemPosition()).getValue());
                        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.otpVerifierFragment, data);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        VerifyPhoneError verifyPhoneError = new Gson().fromJson(error, VerifyPhoneError.class);
                        showToast(verifyPhoneError.toString());
                    }

                    @Override
                    public void onNetworkError(String error, int errorCode) {
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
