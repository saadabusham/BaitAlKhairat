package com.saad.baitalkhairat.ui.auth.changepassword;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChangePasswordBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordViewModel extends BaseViewModel<ChangePasswordNavigator, FragmentChangePasswordBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> ChangePasswordViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ChangePasswordNavigator) navigation, (FragmentChangePasswordBinding) viewDataBinding);

    }

    public void changePasswordClicked() {
        if (isValid()) {
            getDataManager().getAuthService().getDataApi().updatePassword(
                    getViewBinding().edNewPassword.getText().toString(), getViewBinding().edConfirmNewPassword.getText().toString(),
                    getViewBinding().edOldPassword.getText().toString()
            )
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponse<String>(getMyContext(), true, new APICallBack<String>() {
                        @Override
                        public void onSuccess(String response) {
                            new OnLineDialog(getMyContext()) {
                                @Override
                                public void onPositiveButtonClicked() {
                                    dismiss();
                                    Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment).popBackStack();
                                }

                                @Override
                                public void onNegativeButtonClicked() {

                                }
                            }.showConfirmationDialog(DialogTypes.OK, getMyContext().getResources().getString(R.string.update_successfully),
                                    getMyContext().getResources().getString(R.string.your_password_has_been_updated_successfully));
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            showSnackBar(getMyContext().getString(R.string.error),
                                    error, getMyContext().getResources().getString(R.string.OK),
                                    new SnackViewBulider.SnackbarCallback() {
                                        @Override
                                        public void onActionClick(Snackbar snackbar) {
                                            snackbar.dismiss();
                                        }
                                    });
                        }
                    }));
        }
    }

    public boolean isValid() {
        int error = 0;

        if (getViewBinding().edOldPassword.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edOldPassword.setError(getMyContext().getString(R.string.old_password_is_required));
        }

        if (getViewBinding().edNewPassword.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edNewPassword.setError(getMyContext().getString(R.string.password_is_required));
        }

        if (!getViewBinding().edNewPassword.getText().toString().equals(getViewBinding().edConfirmNewPassword.getText().toString())) {
            error = +1;
            getViewBinding().edNewPassword.setError(getMyContext().getString(R.string.does_not_match));
            getViewBinding().edConfirmNewPassword.setError(getMyContext().getString(R.string.does_not_match));
        }
        return error == 0;
    }

    @Override
    protected void setUp() {

    }
}
