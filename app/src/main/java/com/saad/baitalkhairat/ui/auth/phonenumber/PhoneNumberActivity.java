package com.saad.baitalkhairat.ui.auth.phonenumber;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityPhoneNumberBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class PhoneNumberActivity extends BaseActivity<ActivityPhoneNumberBinding, PhoneNumberViewModel>
        implements PhoneNumberNavigator {

    int type;
    @Inject
    ViewModelProviderFactory factory;
    private PhoneNumberViewModel mPhoneNumberViewModel;
    private ActivityPhoneNumberBinding mViewBinding;

    public static Intent newIntent(Context context, int type) {
        return new Intent(context, PhoneNumberActivity.class).putExtra("type", type);
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
        return R.layout.activity_phone_number;
    }

    @Override
    public PhoneNumberViewModel getViewModel() {
        mPhoneNumberViewModel = (PhoneNumberViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(PhoneNumberViewModel.class, getViewDataBinding(), this);
        return mPhoneNumberViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();
        type = getIntent().getIntExtra("type", 0);
        mPhoneNumberViewModel.setType(type);

    }

}