package com.saad.baitalkhairat.ui.walletjourney.chargedsuccessful;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChargedSuccessfulBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;

public class ChargedSuccessfulViewModel extends BaseViewModel<ChargedSuccessfulNavigator, FragmentChargedSuccessfulBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> ChargedSuccessfulViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ChargedSuccessfulNavigator) navigation, (FragmentChargedSuccessfulBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

    }

    public void onApplyDonationClick() {
        Bundle data = new Bundle();
        data.putInt(AppConstants.BundleData.CATEGORY_ID, 0);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_chargedSuccessfulFragment_to_casesFragment, data);
    }

    public void onHomeClicked() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_chargedSuccessfulFragment_to_nav_home);
    }
}
