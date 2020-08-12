package com.saad.baitalkhairat.ui.menu.about_us;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentAboutUsBinding;
import com.saad.baitalkhairat.model.app.AboutUs;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class AboutUsViewModel extends BaseViewModel<AboutUsNavigator, FragmentAboutUsBinding> {


    public <V extends ViewDataBinding, N extends BaseNavigator> AboutUsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (AboutUsNavigator) navigation, (FragmentAboutUsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

        getData();
    }

    private void getData() {
        getDataManager().getAppService().getAboutUs(getMyContext(), true, new APICallBack<AboutUs>() {
            @Override
            public void onSuccess(AboutUs response) {
                getViewBinding().setData(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }
}
