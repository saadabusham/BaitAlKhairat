package com.saad.baitalkhairat.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentHomeBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mViewBinding;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
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
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        mHomeViewModel = (HomeViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(HomeViewModel.class, getViewDataBinding(), this);
        return mHomeViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mHomeViewModel.setUp();
    }

    @Override
    public FragmentManager getChildFragment() {
        return getChildFragmentManager();
    }

}
