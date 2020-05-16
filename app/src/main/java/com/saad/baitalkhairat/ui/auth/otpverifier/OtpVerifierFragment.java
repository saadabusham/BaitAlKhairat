package com.saad.baitalkhairat.ui.auth.otpverifier;

import android.content.Context;
import android.content.Intent;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentOtpVerifierBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class OtpVerifierFragment extends BaseFragment<FragmentOtpVerifierBinding, OtpVerifierViewModel>
        implements OtpVerifierNavigator {

    private static final String TAG = OtpVerifierFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    String phone = "";
    int type;
    String token = "";
    private OtpVerifierViewModel mOtpViewModel;
    private FragmentOtpVerifierBinding mViewBinding;

    public static Intent getStartIntent(Context context, String phone, int type, String token) {
        Intent intent = new Intent(context, OtpVerifierFragment.class);
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
    public boolean hideToolbar() {
        return true;
    }

    @Override
    public boolean hideBottomSheet() {
        return true;
    }

    @Override
    public boolean isNeedActivityResult() {
        return false;
    }

    @Override
    public boolean hasOptionMenu() {
        return false;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_otp_verifier;
    }

    @Override
    public OtpVerifierViewModel getViewModel() {
        mOtpViewModel = (OtpVerifierViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(OtpVerifierViewModel.class, getViewDataBinding(), this);
        return mOtpViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
//        phone = getIntent().getStringExtra("phone");
//        token = getIntent().getStringExtra("token");
        type = getArguments().getInt("type", 0);
        mOtpViewModel.setType(type);
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.verification_code);
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