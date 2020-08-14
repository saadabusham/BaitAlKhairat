package com.saad.baitalkhairat.ui.walletjourney.wallet;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentWalletBinding;
import com.saad.baitalkhairat.model.wallet.Wallet;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.LanguageUtils;

public class WalletViewModel extends BaseViewModel<WalletNavigator, FragmentWalletBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> WalletViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (WalletNavigator) navigation, (FragmentWalletBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        getViewBinding().layoutNoDataFound.setViewModel(this);
        getWalletData();
    }

    private void getWalletData() {
        getDataManager().getWalletService().getWallet(getMyContext(), new APICallBack<Wallet>() {
            @Override
            public void onSuccess(Wallet response) {
                getViewBinding().setData(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }


    public void onChargeClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_walletFragment_to_chargeToFragment);
    }


    public void onApplyNeedClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_walletFragment_to_applyNeedsFragment);
    }

    public void onApplyDonationClick() {
        Bundle data = new Bundle();
        data.putInt(AppConstants.BundleData.CATEGORY_ID, 0);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_walletFragment_to_casesFragment, data);
    }

    public void onTransactionsClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_walletFragment_to_transactionsFragment);
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }
}
