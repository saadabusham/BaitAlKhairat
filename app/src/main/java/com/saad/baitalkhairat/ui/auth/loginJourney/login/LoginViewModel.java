package com.saad.baitalkhairat.ui.auth.loginJourney.login;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentLoginBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.LoginObject;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.errormodel.LoginError;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBackNew;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNew;
import com.saad.baitalkhairat.repository.network.ApiConstants;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel<LoginNavigator, FragmentLoginBinding> {

    private static final String TAG = "LoginViewModel";
    ArrayList<ListItem> countryCodeList = new ArrayList<>();
    ArrayAdapter<ListItem> countryCodeAdapter;
    TextWatcher textWatcher = new TextWatcher() {
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
    };

    public <V extends ViewDataBinding, N extends BaseNavigator> LoginViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (LoginNavigator) navigation, (FragmentLoginBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        SpannableString content = new SpannableString(getMyContext().getResources().getString(R.string.forget_password));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        getViewBinding().tvForgetPassword.setText(content);
        setUpSpinnerCountry();

        setUpWatcher();

    }

    private void setUpWatcher() {
        getViewBinding().edPhoneNumber.addTextChangedListener(textWatcher);
        getViewBinding().edPassword.addTextChangedListener(textWatcher);
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnLogin.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnLogin.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnLogin.setEnabled(true);
        } else {
            getViewBinding().btnLogin.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnLogin.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnLogin.setEnabled(false);
        }
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

    public void loginClick() {
        if (isValidate()) {
            getDataManager().getAuthService().getDataApi().loginUser(getLoginObj(), ApiConstants.PASSPORT_CLIENT_ID, ApiConstants.PASSPORT_CLIENT_SECRET)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponseNew<User, LoginError>(getMyContext(), true, new APICallBackNew<User>() {
                        @Override
                        public void onSuccess(User response) {
                            User.getInstance().setObjUser(response);
                            SessionManager.createUserLoginSession();
                            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                    .navigate(R.id.action_loginFragment_to_nav_home);
//                            getDataManager().getAuthService().reInit();
//                            getDataManager().getAuthService().updateFirebaseToken(getMyContext(), true, new APICallBack() {
//                                @Override
//                                public void onSuccess(Object response) {
//                                    getBaseActivity().finishAffinity();
////                                    getBaseActivity().startActivity(MainActivity.newIntent(getMyContext()));
//                                }
//
//                                @Override
//                                public void onError(String error, int errorCode) {
//                                    showToast(error);
//                                }
//                            });
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            LoginError loginError = new Gson().fromJson(error, LoginError.class);
                            showToast(error);
                        }

                        @Override
                        public void onNetworkError(String error, int errorCode) {
                            showToast(error);
                        }
                    }));
        }
    }

    private LoginObject getLoginObj() {
        return new LoginObject(getViewBinding().edPhoneNumber.getText().toString().trim(),
                countryCodeAdapter.getItem(getViewBinding().spinnerCountryCode.getSelectedItemPosition()).getValue(),
                getViewBinding().edPassword.getText().toString(), DeviceUtils.getMacAddr());
    }

    private boolean isValidate() {
        int error = 0;
        if (getViewBinding().edPhoneNumber.getText().toString().isEmpty()) {
            getViewBinding().edPhoneNumber.setError(getMyContext().getString(R.string.the_phone_number_is_required));
            error = +1;
        }
        if (getViewBinding().edPassword.getText().toString().isEmpty()) {
            getViewBinding().edPassword.setError(getMyContext().getString(R.string.this_fieled_is_required));
            error = +1;
        }
        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);
        return error == 0;
    }

    public void forgetPasswordClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.verifyPhoneNumberFragment);
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }
}
