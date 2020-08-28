package com.saad.baitalkhairat.ui.donatejourney.donationappliedsuccessful;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentDonorAppliedSuccessfulBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class DonorAppliedSuccessfulViewModel extends BaseViewModel<DonorAppliedSuccessfulNavigator, FragmentDonorAppliedSuccessfulBinding> {


    public <V extends ViewDataBinding, N extends BaseNavigator> DonorAppliedSuccessfulViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (DonorAppliedSuccessfulNavigator) navigation, (FragmentDonorAppliedSuccessfulBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

    }

    public void onHomeClicked() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_donorAppliedSuccessfulFragment_to_nav_home);
    }

    public void onControlPanelClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_donorAppliedSuccessfulFragment_to_nav_account);
    }

}
