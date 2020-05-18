package com.saad.baitalkhairat.ui.needjourney.needappliedsuccessful;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNeedAppliedSuccessfulBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class NeedAppliedSuccessfulViewModel extends BaseViewModel<NeedAppliedSuccessfulNavigator, FragmentNeedAppliedSuccessfulBinding> {


    public <V extends ViewDataBinding, N extends BaseNavigator> NeedAppliedSuccessfulViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (NeedAppliedSuccessfulNavigator) navigation, (FragmentNeedAppliedSuccessfulBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

    }

    public void onHomeClicked() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_needAppliedFragment_to_nav_home);
    }

    public void onControlPanelClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_needAppliedFragment_to_nav_account);
    }

}
