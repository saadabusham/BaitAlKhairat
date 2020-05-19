package com.saad.baitalkhairat.ui.walletjourney.wallet;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentWalletBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class WalletFragment extends BaseFragment<FragmentWalletBinding, WalletViewModel> implements WalletNavigator {

    private static final String TAG = WalletFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private WalletViewModel mViewModel;
    private FragmentWalletBinding mViewBinding;

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
        return R.layout.fragment_wallet;
    }

    @Override
    public WalletViewModel getViewModel() {
        mViewModel = (WalletViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(WalletViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.my_wallet);
        mViewModel.setUp();
    }

}
