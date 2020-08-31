package com.saad.baitalkhairat.ui.profilejourney.usersociallinks;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentUserSocialLinksBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.account.ProfileResponse;
import com.saad.baitalkhairat.model.account.UserResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.utils.LanguageUtils;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserSocialLinksViewModel extends BaseViewModel<UserSocialLinksNavigator, FragmentUserSocialLinksBinding> {


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

    String bindingKey = "";

    public <V extends ViewDataBinding, N extends BaseNavigator> UserSocialLinksViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (UserSocialLinksNavigator) navigation, (FragmentUserSocialLinksBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        bindingKey = GeneralFunction.generateUUID();
        getViewBinding().setData(getNavigator().getUser());
        setUpWatcher();
    }

    private void setUpWatcher() {
        getViewBinding().edFacebookLink.addTextChangedListener(textWatcher);
        getViewBinding().edTwitterLink.addTextChangedListener(textWatcher);
        getViewBinding().edYoutubeLink.addTextChangedListener(textWatcher);
        getViewBinding().edInstagramLink.addTextChangedListener(textWatcher);
        getViewBinding().edLinkedInLink.addTextChangedListener(textWatcher);
    }


    public void onSaveClick() {
        if (isValid()) {
            getDataManager().getAuthService().getDataApi().updateProfile(getUserObj())
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponse<ProfileResponse>(getMyContext(), true, new APICallBack<ProfileResponse>() {
                        @Override
                        public void onSuccess(ProfileResponse response) {
                            new OnLineDialog(getMyContext()) {
                                @Override
                                public void onPositiveButtonClicked() {
                                    dismiss();
                                    Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                            .navigate(R.id.action_userSocialLinksFragment_to_nav_account);
                                }

                                @Override
                                public void onNegativeButtonClicked() {

                                }
                            }.showConfirmationDialog(DialogTypes.OK, getMyContext().getResources().getString(R.string.update_successfully),
                                    getMyContext().getResources().getString(R.string.your_profile_has_been_updated_successfully));
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

    private UserResponse getUserObj() {
        getNavigator().getUser().setBinding_key(bindingKey);
        getNavigator().getUser().setSocialFacebookLink(getViewBinding().edFacebookLink.getText().toString());
        getNavigator().getUser().setSocialYoutubeLink(getViewBinding().edYoutubeLink.getText().toString());
        getNavigator().getUser().setSocialInstagramLink(getViewBinding().edInstagramLink.getText().toString());
        getNavigator().getUser().setSocialTwitterLink(getViewBinding().edTwitterLink.getText().toString());
        getNavigator().getUser().setSocialLinkedinLink(getViewBinding().edLinkedInLink.getText().toString());
        return getNavigator().getUser();
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnSave.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnSave.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnSave.setEnabled(true);
        } else {
            getViewBinding().btnSave.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnSave.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnSave.setEnabled(false);
        }
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }

    public boolean isValid() {
        int error = 0;

        if (!getViewBinding().edFacebookLink.getText().toString().trim().isEmpty()) {
            error = +1;
        }

        if (!getViewBinding().edTwitterLink.getText().toString().trim().isEmpty()) {
            error = +1;
        }

        if (!getViewBinding().edYoutubeLink.getText().toString().trim().isEmpty()) {
            error = +1;
        }

        if (!getViewBinding().edInstagramLink.getText().toString().trim().isEmpty()) {
            error = +1;
        }

        if (!getViewBinding().edLinkedInLink.getText().toString().trim().isEmpty()) {
            error = +1;
        }

        if (error == 0)
            checkValidate(false);
        else
            checkValidate(true);
        return error > 0;
    }
}
