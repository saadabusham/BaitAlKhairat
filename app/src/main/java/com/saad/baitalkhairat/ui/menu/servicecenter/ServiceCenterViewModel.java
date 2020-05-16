package com.saad.baitalkhairat.ui.menu.servicecenter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentServiceCenterBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;

public class ServiceCenterViewModel extends BaseViewModel<ServiceCenterNavigator, FragmentServiceCenterBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> ServiceCenterViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ServiceCenterNavigator) navigation, (FragmentServiceCenterBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

    }

    public void onMostCommonClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.questionsFragment);
    }

    public void onFacebookClick() {

    }

    public void onInstagramClick() {

    }

    public void onTwitterClick() {

    }

    public void onPhoneClicked() {
        DeviceUtils.copyToClipboard(getMyContext(), getViewBinding().tvPhone.getText().toString());
    }

    public void onEmailClick() {
        DeviceUtils.copyToClipboard(getMyContext(), getViewBinding().tvEmail.getText().toString());

    }
}
