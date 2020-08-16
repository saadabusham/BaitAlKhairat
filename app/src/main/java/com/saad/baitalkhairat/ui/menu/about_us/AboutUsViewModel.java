package com.saad.baitalkhairat.ui.menu.about_us;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentAboutUsBinding;
import com.saad.baitalkhairat.model.app.aboutus.AboutUs;
import com.saad.baitalkhairat.model.app.aboutus.AboutUsSectionsResponse;
import com.saad.baitalkhairat.model.app.aboutus.BaitAlKhairatResources;
import com.saad.baitalkhairat.model.app.aboutus.FundingResourceResponse;
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

        getAboutUs();
        getAboutUsSections();
        getFundingResource();
        getBaitAlkhairatResource();
    }

    private void getAboutUs() {
        getDataManager().getAppService().getAboutUs(getMyContext(), true, new APICallBack<AboutUs>() {
            @Override
            public void onSuccess(AboutUs response) {
                getViewBinding().setAboutUs(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void getAboutUsSections() {
        getDataManager().getAppService().getAboutUsSections(getMyContext(), true, new APICallBack<AboutUsSectionsResponse>() {
            @Override
            public void onSuccess(AboutUsSectionsResponse response) {
                getViewBinding().setAboutUsSections(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void getFundingResource() {
        getDataManager().getAppService().getFundingResource(getMyContext(), true, new APICallBack<FundingResourceResponse>() {
            @Override
            public void onSuccess(FundingResourceResponse response) {
                getViewBinding().setFundingResources(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void getBaitAlkhairatResource() {
        getDataManager().getAppService().getBaitResource(getMyContext(), true, new APICallBack<BaitAlKhairatResources>() {
            @Override
            public void onSuccess(BaitAlKhairatResources response) {
                getViewBinding().setBaitAlkhairatResources(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }
}
