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

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentLoginBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.RegisterResponse;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel<LoginNavigator, FragmentLoginBinding> {

    private static final String TAG = "LoginViewModel";

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
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.jordan_code));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountryCode.setAdapter(adapter);
    }


    public void loginClick() {
        if (isValidate()) {
            getDataManager().getAuthService().getDataApi().loginUser(getViewBinding().edPhoneNumber.getText().toString().trim(),
                    getViewBinding().edPassword.getText().toString())
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponse<RegisterResponse>(getMyContext(), true, new APICallBack<RegisterResponse>() {
                        @Override
                        public void onSuccess(RegisterResponse response) {
                            User user = response.getUser();
                            user.setToken(response.getJwt_token());
                            User.getInstance().setObjUser(user);
                            SessionManager.createUserLoginSession();
                            getDataManager().getAuthService().reInit();
                            getDataManager().getAuthService().updateFirebaseToken(getMyContext(), true, new APICallBack() {
                                @Override
                                public void onSuccess(Object response) {
                                    getBaseActivity().finishAffinity();
//                                    getBaseActivity().startActivity(MainActivity.newIntent(getMyContext()));
                                }

                                @Override
                                public void onError(String error, int errorCode) {
                                    showToast(error);
                                }
                            });
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            showToast(error);
                        }
                    }));
        }
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
