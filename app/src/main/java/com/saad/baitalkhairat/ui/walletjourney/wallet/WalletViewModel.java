package com.saad.baitalkhairat.ui.walletjourney.wallet;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentWalletBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class WalletViewModel extends BaseViewModel<WalletNavigator, FragmentWalletBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> WalletViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (WalletNavigator) navigation, (FragmentWalletBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        getViewBinding().layoutNoDataFound.setViewModel(this);
    }

    public void onChargeClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_walletFragment_to_chargeToFragment);
    }
}
