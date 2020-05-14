package com.saad.baitalkhairat.ui.auth.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityRegisterBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel>
        implements RegisterNavigator {

    @Inject
    ViewModelProviderFactory factory;
    String token = "";
    private RegisterViewModel mRegisterViewModel;
    private ActivityRegisterBinding mViewBinding;

    public static Intent newIntent(Context context, String token) {
        return new Intent(context, RegisterActivity.class).putExtra("token", token);
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
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterViewModel = (RegisterViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(RegisterViewModel.class, getViewDataBinding(), this);
        return mRegisterViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();
        token = getIntent().getStringExtra("token");
        mRegisterViewModel.setUp();

    }

    @Override
    public String getToken() {
        return token;
    }
}