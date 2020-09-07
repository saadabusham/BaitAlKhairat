package com.saad.baitalkhairat.ui.auth.loginJourney.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentLoginBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.auth.LoginObject;
import com.saad.baitalkhairat.model.auth.TokenResponse;
import com.saad.baitalkhairat.model.auth.User;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.errormodel.LoginError;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNoStandardLogin;
import com.saad.baitalkhairat.repository.network.ApiConstants;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel<LoginNavigator, FragmentLoginBinding> {

    NavOptions navOptions;
    NavOptions.Builder navBuilder = new NavOptions.Builder();

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
        navOptions = navBuilder.setPopUpTo(R.id.nav_graph, false).build();
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
//        getViewBinding().spinnerCountryCode.setAdapter(countryCodeAdapter);

        getViewBinding().autoComTextView.setThreshold(1);
        getViewBinding().autoComTextView.setAdapter(countryCodeAdapter);

        getViewBinding().autoComTextView.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                if (countryCodeAdapter.getCount() > 0) {
                    if (!getViewBinding().autoComTextView.getText().toString().equals(""))
                        countryCodeAdapter.getFilter().filter(null);
                    getViewBinding().autoComTextView.showDropDown();
                    isValidate();
                }
                return false;
            }
        });
        getViewBinding().autoComTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                getViewBinding().autoComTextView.setText(countryCodeAdapter.getItem(position).getLabel());
                isValidate();
            }
        });

        getViewBinding().autoComTextView.addTextChangedListener(textWatcher);
        getCountryCodes();
    }

    private void getCountryCodes() {
        getDataManager().getAppService().getCountryCode(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                countryCodeAdapter.addAll(response.getList());
                countryCodeAdapter.notifyDataSetChanged();
                getViewBinding().autoComTextView.setText(response.getList().get(0).getLabel());
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
                    .subscribe(new CustomObserverResponseNoStandardLogin<TokenResponse>(getMyContext(), true, new APICallBack<TokenResponse>() {
                        @Override
                        public void onSuccess(TokenResponse response) {
                            if (response.getLoginError() == null) {
                                response.setToken_generated_date(Calendar.getInstance().getTimeInMillis());
                                User.getInstance().setTokenResponse(response);
                                SessionManager.createUserLoginSession();
                                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                        .navigate(R.id.nav_home, null, navOptions);
                            } else {
                                showToast(response.getLoginError().toString());

                                if (response.getLoginError().getPhone() != null && response.getLoginError().getPhone().size() > 0) {
                                    getViewBinding().edPhoneNumber.setError(response.getLoginError().getPhone().toString());
                                }
                                if (response.getLoginError().getPassword() != null && response.getLoginError().getPassword().size() > 0) {
                                    getViewBinding().edPassword.setError(response.getLoginError().getPassword().toString());
                                }
                            }
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            if (errorCode == 7777) {
                                LoginError loginError = new Gson().fromJson(error, LoginError.class);
                                if (loginError.getPhone() != null && loginError.getPhone().size() > 0) {
                                    getViewBinding().edPhoneNumber.setError(loginError.getPhone().toString());
                                }
                                if (loginError.getPassword() != null && loginError.getPassword().size() > 0) {
                                    getViewBinding().edPassword.setError(loginError.getPassword().toString());
                                }
                            }
                        }
                    }));
        }
    }

    private LoginObject getLoginObj() {
        return new LoginObject(getViewBinding().edPhoneNumber.getText().toString().trim(),
                getSelectedCountryItem(getViewBinding().autoComTextView.getText().toString()),
                getViewBinding().edPassword.getText().toString(), DeviceUtils.getUDID(getBaseActivity()));
    }

    private String getSelectedCountryItem(String text) {
        for (int i = 0; i < countryCodeAdapter.getCount(); i++) {
            if (countryCodeAdapter.getItem(i).getLabel().equals(text))
                return countryCodeAdapter.getItem(i).getValue();
        }
        return "";
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

        if (getViewBinding().autoComTextView.getText().toString().isEmpty()) {
            getViewBinding().autoComTextView.setError(getMyContext().getString(R.string.this_fieled_is_required));
            error = +1;
        } else {
            getViewBinding().autoComTextView.setError(null);
        }

        if (getSelectedCountryItem(getViewBinding().autoComTextView.getText().toString()).isEmpty()) {
            getViewBinding().autoComTextView.setError(getMyContext().getString(R.string.please_choose_valid_code));
            error = +1;
        } else if (getViewBinding().autoComTextView.getError() != null &&
                getViewBinding().autoComTextView.getError().length() == 0) {
            getViewBinding().autoComTextView.setError(null);
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
