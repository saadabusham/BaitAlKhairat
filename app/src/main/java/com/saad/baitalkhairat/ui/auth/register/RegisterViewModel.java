package com.saad.baitalkhairat.ui.auth.register;

import android.content.Context;
import android.util.Patterns;

import androidx.databinding.ViewDataBinding;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityRegisterBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.RegisterResponse;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator, ActivityRegisterBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> RegisterViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (RegisterNavigator) navigation, (ActivityRegisterBinding) viewDataBinding);
    }

    public void privacyPolicyClicked() {

    }

    public void registerClicked() {
        if (isValid()) {
            if (!getViewBinding().checkboxReadTerm.isChecked()) {
                ((RegisterActivity) getMyContext()).showSnackBar(getViewBinding().getRoot(), R.drawable.ic_warning,
                        getMyContext().getResources().getString(R.string.privacy_policy),
                        getMyContext().getResources().getString(R.string.you_should_accpet_our_condition_and_terms),
                        getMyContext().getResources().getString(R.string.OK), new SnackViewBulider.SnackbarCallback() {
                            @Override
                            public void onActionClick(Snackbar snackbar) {
                                snackbar.dismiss();
                            }
                        });
                return;
            }
            registerUser();
        }
    }

    public void registerUser() {
        getDataManager().getAuthService().getDataApi().registerUser(getUserObj())
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
//                        getDataManager().getAuthervice().setObjNull();
                        getDataManager().getAuthService().updateFirebaseToken(getMyContext(), true, new APICallBack() {
                            @Override
                            public void onSuccess(Object response) {
                                getBaseActivity().finishAffinity();
//                                getBaseActivity().startActivity(MainActivity.newIntent(getMyContext()));
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

    private User getUserObj() {
        User user = User.getInstance();
        user.setEmail(getViewBinding().edEmail.getText().toString().trim());
        user.setName(getViewBinding().edUserName.getText().toString());
        user.setToken(getNavigator().getToken());
        user.setPassword(getViewBinding().edPassword.getText().toString());
        user.setPassword_confirmation(getViewBinding().edConfirmPassword.getText().toString());
        return user;
    }

    public boolean isValid() {
        int error = 0;

        if (!Patterns.EMAIL_ADDRESS.matcher(getViewBinding().edEmail.getText().toString().trim()).matches()) {
            error = +1;
            getViewBinding().edEmail.setError(getMyContext().getString(R.string.email_is_required));
        }

        if (getViewBinding().edUserName.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edUserName.setError(getMyContext().getString(R.string.user_name_is_required));
        }

        if (getViewBinding().edUserName.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edUserName.setError(getMyContext().getString(R.string.user_name_is_required));
        }

        if (getViewBinding().edPassword.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edPassword.setError(getMyContext().getString(R.string.password_is_required));
        }

        if (!getViewBinding().edPassword.getText().toString().equals(getViewBinding().edConfirmPassword.getText().toString())) {
            error = +1;
            getViewBinding().edPassword.setError(getMyContext().getString(R.string.does_not_match));
            getViewBinding().edConfirmPassword.setError(getMyContext().getString(R.string.does_not_match));
        }

        return error == 0;
    }

    @Override
    protected void setUp() {

    }
}
