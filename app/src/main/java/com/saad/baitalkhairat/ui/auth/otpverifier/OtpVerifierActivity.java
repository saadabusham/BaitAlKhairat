package com.saad.baitalkhairat.ui.auth.otpverifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityOtpVerifierBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class OtpVerifierActivity extends BaseActivity<ActivityOtpVerifierBinding, OtpVerifierViewModel>
        implements OtpVerifierNavigator {

    @Inject
    ViewModelProviderFactory factory;
    String phone = "";
    int type;
    String token = "";
    private OtpVerifierViewModel mOtpViewModel;
    private ActivityOtpVerifierBinding mViewBinding;

    public static Intent getStartIntent(Context context, String phone, int type, String token) {
        Intent intent = new Intent(context, OtpVerifierActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("type", type);
        intent.putExtra("token", token);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public void setUpToolbar() {
        setUpToolbar(getViewDataBinding().toolbar.toolbar, R.string.app_name, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_otp_verifier;
    }

    @Override
    public OtpVerifierViewModel getViewModel() {
        mOtpViewModel = (OtpVerifierViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(OtpVerifierViewModel.class, getViewDataBinding(), this);
        return mOtpViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();
        phone = getIntent().getStringExtra("phone");
        token = getIntent().getStringExtra("token");
        type = getIntent().getIntExtra("type", 0);
        mOtpViewModel.setType(type);
        mOtpViewModel.setUp();
    }

    public String getPhone() {
        return phone;
    }

    public int getType() {
        return type;
    }

    @Override
    public String getToken() {
        return token;
    }
}