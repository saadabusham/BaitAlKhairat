package com.saad.baitalkhairat.ui.auth.changepassword;

import android.content.Context;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChangePasswordBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ChangePasswordFragment extends BaseFragment<FragmentChangePasswordBinding, ChangePasswordViewModel> implements ChangePasswordNavigator {

    private static final String TAG = ChangePasswordFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ChangePasswordViewModel mChangePasswordViewModel;
    private FragmentChangePasswordBinding mViewBinding;

    public static ChangePasswordFragment newInstance() {
        Bundle args = new Bundle();
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean hasOptionMenu() {
        return false;
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
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_change_password;
    }

    @Override
    public ChangePasswordViewModel getViewModel() {
        mChangePasswordViewModel = (ChangePasswordViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ChangePasswordViewModel.class, getViewDataBinding(), this);
        return mChangePasswordViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mChangePasswordViewModel.setUp();
        setUpToolbar(mViewBinding.toolbar, TAG, getMyContext().getResources().getString(R.string.change_password));
    }
}
