package com.saad.baitalkhairat.ui.auth.createpassword;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCreatePasswordBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.auth.loginJourney.login.LoginFragment;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.utils.LanguageUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreatePasswordViewModel extends BaseViewModel<CreatePasswordNavigator, FragmentCreatePasswordBinding> {

    String token = "";

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isValid();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public <V extends ViewDataBinding, N extends BaseNavigator> CreatePasswordViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (CreatePasswordNavigator) navigation, (FragmentCreatePasswordBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        token = getNavigator().getToken();
        getViewBinding().edNewPassword.addTextChangedListener(textWatcher);
        getViewBinding().edConfirmPassword.addTextChangedListener(textWatcher);
    }

    public boolean isValid() {
        int error = 0;
        if (getViewBinding().edNewPassword.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edNewPassword.setError(getMyContext().getString(R.string.password_is_required));
        }

        if (!getViewBinding().edNewPassword.getText().toString().equals(getViewBinding().edConfirmPassword.getText().toString())) {
            error = +1;
            getViewBinding().edNewPassword.setError(getMyContext().getString(R.string.does_not_match));
            getViewBinding().edConfirmPassword.setError(getMyContext().getString(R.string.does_not_match));
        }
        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);
        return error == 0;
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnCreate.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnCreate.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnCreate.setEnabled(true);
        } else {
            getViewBinding().btnCreate.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnCreate.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnCreate.setEnabled(false);
        }
    }

    public void createPassword() {
        if (isValid()) {
            getDataManager().getAuthService().getDataApi().createPassword(getViewBinding().edNewPassword.getText().toString(),
                    getViewBinding().edConfirmPassword.getText().toString(), token)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponse<String>(getMyContext(), true, new APICallBack<String>() {
                        @Override
                        public void onSuccess(String response) {
                            SessionManager.logoutUser();
                            new OnLineDialog(getMyContext()) {
                                @Override
                                public void onPositiveButtonClicked() {
                                    getBaseActivity().finishAffinity();
                                    getBaseActivity().startActivity(new Intent(getMyContext(),
                                            LoginFragment.class));
                                }

                                @Override
                                public void onNegativeButtonClicked() {

                                }
                            }.showConfirmationDialog(DialogTypes.OK, getMyContext().getString(R.string.update_successfully),
                                    getMyContext().getResources().getString(R.string.you_can_login_now));

                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            showToast(error);
                        }
                    }));
        }
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }
}
