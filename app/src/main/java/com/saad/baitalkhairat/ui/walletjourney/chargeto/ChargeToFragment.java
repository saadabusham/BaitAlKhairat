package com.saad.baitalkhairat.ui.walletjourney.chargeto;

import android.content.Context;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChargeToBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ChargeToFragment extends BaseFragment<FragmentChargeToBinding, ChargeToViewModel> implements ChargeToNavigator {

    private static final String TAG = ChargeToFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ChargeToViewModel mViewModel;
    private FragmentChargeToBinding mViewBinding;


    public static ChargeToFragment newInstance() {
        Bundle args = new Bundle();
        ChargeToFragment fragment = new ChargeToFragment();
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
        return R.layout.fragment_charge_to;
    }

    @Override
    public ChargeToViewModel getViewModel() {
        mViewModel = (ChargeToViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ChargeToViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.charge_wallet);
        mViewModel.setUp();
    }

}
