package com.saad.baitalkhairat.ui.settings;

import android.content.Context;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentSettingsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class SettingsFragment extends BaseFragment<FragmentSettingsBinding, SettingsViewModel> implements SettingsNavigator {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SettingsViewModel mOffersViewModel;
    private FragmentSettingsBinding mViewBinding;


    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean hasOptionMenu() {
        return false;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getViewModel().setUp();
//    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public boolean hideToolbar() {
        return false;
    }

    @Override
    public boolean hideBottomSheet() {
        return false;
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
        return R.layout.fragment_settings;
    }

    @Override
    public SettingsViewModel getViewModel() {
        mOffersViewModel = (SettingsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(SettingsViewModel.class, getViewDataBinding(), this);
        return mOffersViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mOffersViewModel.setUp();
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void showToast(String message) {

    }


}
