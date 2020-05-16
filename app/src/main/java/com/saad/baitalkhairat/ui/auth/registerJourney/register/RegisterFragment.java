package com.saad.baitalkhairat.ui.auth.registerJourney.register;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentRegisterBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, RegisterViewModel>
        implements RegisterNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private RegisterViewModel mRegisterViewModel;
    private FragmentRegisterBinding mViewBinding;


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
        return R.layout.fragment_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterViewModel = (RegisterViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(RegisterViewModel.class, getViewDataBinding(), this);
        return mRegisterViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mRegisterViewModel.setUp();
    }
}