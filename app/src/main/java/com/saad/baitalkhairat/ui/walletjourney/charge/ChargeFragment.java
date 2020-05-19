package com.saad.baitalkhairat.ui.walletjourney.charge;

import android.content.Context;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChargeBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ChargeFragment extends BaseFragment<FragmentChargeBinding, ChargeViewModel> implements ChargeNavigator {

    private static final String TAG = ChargeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ChargeViewModel mViewModel;
    private FragmentChargeBinding mViewBinding;


    public static ChargeFragment newInstance() {
        Bundle args = new Bundle();
        ChargeFragment fragment = new ChargeFragment();
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
        return R.layout.fragment_charge;
    }

    @Override
    public ChargeViewModel getViewModel() {
        mViewModel = (ChargeViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ChargeViewModel.class, getViewDataBinding(), this);
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

    @Override
    public int getChargeTo() {
        return getArguments().getInt(AppConstants.BundleData.CHARGE_TO_TYPES, 1);
    }
}
