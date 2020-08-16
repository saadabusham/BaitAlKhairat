package com.saad.baitalkhairat.ui.menu.servicecenter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentServiceCenterBinding;
import com.saad.baitalkhairat.model.app.ContactUs;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;

public class ServiceCenterViewModel extends BaseViewModel<ServiceCenterNavigator, FragmentServiceCenterBinding> {

    ContactUs contactUs = new ContactUs();

    public <V extends ViewDataBinding, N extends BaseNavigator> ServiceCenterViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ServiceCenterNavigator) navigation, (FragmentServiceCenterBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

        getData();
    }

    private void getData() {
        getDataManager().getAppService().getContactUs(getMyContext(), true, new APICallBack<ContactUs>() {
            @Override
            public void onSuccess(ContactUs response) {
                getViewBinding().setData(response);
                contactUs = response;
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    public void onMostCommonClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.questionsFragment);
    }

    public void onFacebookClick() {
        DeviceUtils.openUrl(getMyContext(), contactUs.getFacebookLink());
    }

    public void onInstagramClick() {
        DeviceUtils.openUrl(getMyContext(), contactUs.getLinkedinLink());
    }

    public void onTwitterClick() {
        DeviceUtils.openUrl(getMyContext(), contactUs.getTwitterLink());
    }

    public void onPhoneClicked() {
        DeviceUtils.openDialer(getMyContext(), getViewBinding().tvPhone.getText().toString());
    }

    public void onEmailClick() {
        DeviceUtils.intentEmail(getMyContext(), getViewBinding().tvEmail.getText().toString());

    }
}
