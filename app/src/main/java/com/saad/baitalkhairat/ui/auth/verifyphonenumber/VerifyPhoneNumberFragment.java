package com.saad.baitalkhairat.ui.auth.verifyphonenumber;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentVerifyPhoneNumberBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class VerifyPhoneNumberFragment extends BaseFragment<FragmentVerifyPhoneNumberBinding, VerifyPhoneNumberViewModel>
        implements VerifyPhoneNumberNavigator {

    private static final String TAG = VerifyPhoneNumberFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private VerifyPhoneNumberViewModel mPhoneNumberViewModel;
    private FragmentVerifyPhoneNumberBinding mViewBinding;

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
        return R.layout.fragment_verify_phone_number;
    }

    @Override
    public VerifyPhoneNumberViewModel getViewModel() {
        mPhoneNumberViewModel = (VerifyPhoneNumberViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(VerifyPhoneNumberViewModel.class, getViewDataBinding(), this);
        return mPhoneNumberViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.reset_password);
        mPhoneNumberViewModel.setUp();
    }

}