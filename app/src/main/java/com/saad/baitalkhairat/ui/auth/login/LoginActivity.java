package com.saad.baitalkhairat.ui.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityLoginBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import static com.saad.baitalkhairat.ui.auth.login.LoginViewModel.GOOGLE_SIGN_IN;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
        implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mViewBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public void setUpToolbar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = (LoginViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(LoginViewModel.class, getViewDataBinding(), this);
        return mLoginViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();
        mLoginViewModel.setUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mLoginViewModel.getFacebookCallbackManager().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            mLoginViewModel.handleGoogleSignInResult(task);
        }
    }

    @Override
    public void onFragmentAttachedNeedActivityResult(boolean hideToolbar, boolean hideBottomSheet, ActivityResultCallBack activityResultCallBack) {

    }
}