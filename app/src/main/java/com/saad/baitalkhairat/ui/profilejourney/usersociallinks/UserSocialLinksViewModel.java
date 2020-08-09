package com.saad.baitalkhairat.ui.profilejourney.usersociallinks;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentUserSocialLinksBinding;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.LanguageUtils;

import androidx.databinding.ViewDataBinding;

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

    public <V extends ViewDataBinding, N extends BaseNavigator> UserSocialLinksViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (UserSocialLinksNavigator) navigation, (FragmentUserSocialLinksBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
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

    }

    public void registerUser() {
//        getDataManager().getAuthService().getDataApi().registerUser(getUserObj())
//                .toObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new CustomObserverResponse<RegisterResponse>(getMyContext(), true, new APICallBack<RegisterResponse>() {
//                    @Override
//                    public void onSuccess(RegisterResponse response) {
//                        User user = response.getUser();
//                        user.setToken(response.getJwt_token());
//                        User.getInstance().setObjUser(user);
//                        SessionManager.createUserLoginSession();
////                        getDataManager().getAuthervice().setObjNull();
//                        getDataManager().getAuthService().updateFirebaseToken(getMyContext(), true, new APICallBack() {
//                            @Override
//                            public void onSuccess(Object response) {
//                                getBaseActivity().finishAffinity();
////                                getBaseActivity().startActivity(MainActivity.newIntent(getMyContext()));
//                            }
//
//                            @Override
//                            public void onError(String error, int errorCode) {
//                                showToast(error);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(String error, int errorCode) {
//                        showToast(error);
//                    }
//                }));
    }

    private User getUserObj() {
        User user = User.getInstance();
        return user;
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnSignup.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnSignup.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnSignup.setEnabled(true);
        } else {
            getViewBinding().btnSignup.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnSignup.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnSignup.setEnabled(false);
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
        return error == 0;
    }
}
