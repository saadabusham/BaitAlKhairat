package com.saad.baitalkhairat.ui.menu.privacy_policy;

import android.content.Context;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentPrivacyPolicyBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class PrivacyPolicyFragment extends BaseFragment<FragmentPrivacyPolicyBinding, PrivacyPolicyViewModel>
        implements PrivacyPolicyNavigator {

    private static final String TAG = PrivacyPolicyFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private PrivacyPolicyViewModel mViewModel;
    private FragmentPrivacyPolicyBinding mViewBinding;


    public static PrivacyPolicyFragment newInstance() {
        Bundle args = new Bundle();
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        fragment.setArguments(args);
        return fragment;
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
        return R.layout.fragment_privacy_policy;
    }

    @Override
    public PrivacyPolicyViewModel getViewModel() {
        mViewModel = (PrivacyPolicyViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(PrivacyPolicyViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.privacy_policy);
        mViewModel.setUp();
    }

}
