package com.saad.baitalkhairat.ui.walletjourney.chargeto;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChargeToBinding;
import com.saad.baitalkhairat.enums.ChargeToTypes;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;

public class ChargeToViewModel extends BaseViewModel<ChargeToNavigator, FragmentChargeToBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> ChargeToViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ChargeToNavigator) navigation, (FragmentChargeToBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

    }

    public void onChargeMyWalletClick() {

        Bundle data = new Bundle();
        data.putInt(AppConstants.BundleData.CHARGE_TO_TYPES, ChargeToTypes.MY_WALLET.getType());
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_chargeToFragment_to_chargeFragment, data);
    }

    public void onBaitAccountClick() {
        Bundle data = new Bundle();
        data.putInt(AppConstants.BundleData.CHARGE_TO_TYPES, ChargeToTypes.BAIT_AL_KHAIRAT_ACCOUNT.getType());
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_chargeToFragment_to_chargeFragment, data);
    }
}
