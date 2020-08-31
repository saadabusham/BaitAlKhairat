package com.saad.baitalkhairat.ui.menu.about_us;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentAboutUsBinding;
import com.saad.baitalkhairat.model.app.aboutus.AboutUs;
import com.saad.baitalkhairat.model.app.aboutus.AboutUsSectionsResponse;
import com.saad.baitalkhairat.model.app.aboutus.BaitAlKhairatResources;
import com.saad.baitalkhairat.model.app.aboutus.FundingResourceResponse;
import com.saad.baitalkhairat.model.app.aboutus.HumanityValueResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.FundingResourceAdapter;
import com.saad.baitalkhairat.ui.adapter.HumanityValueAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class AboutUsViewModel extends BaseViewModel<AboutUsNavigator, FragmentAboutUsBinding> {


    HumanityValueAdapter humanityValueAdapter;
    FundingResourceAdapter fundingResourceAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> AboutUsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (AboutUsNavigator) navigation, (FragmentAboutUsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpHumanityValuesRecycler();
        setUpFundingResourceRecycler();
        getAboutUs();
        getAboutUsSections();
        getFundingResource();
        getBaitAlkhairatResource();
        getHumanityValues();
    }

    private void setUpHumanityValuesRecycler() {
        getViewBinding().recyclerViewHumanityValues.setLayoutManager(new GridLayoutManager(getMyContext(), 2));
        getViewBinding().recyclerViewHumanityValues.setItemAnimator(new DefaultItemAnimator());
        humanityValueAdapter = new HumanityValueAdapter(getMyContext());
        getViewBinding().recyclerViewHumanityValues.setAdapter(humanityValueAdapter);
    }

    private void setUpFundingResourceRecycler() {
        getViewBinding().recyclerViewFundingResource.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerViewFundingResource.setItemAnimator(new DefaultItemAnimator());
        fundingResourceAdapter = new FundingResourceAdapter(getMyContext());
        getViewBinding().recyclerViewFundingResource.setAdapter(fundingResourceAdapter);
    }


    private void getHumanityValues() {
        getDataManager().getAppService().getHumanityValues(getMyContext(), true, new APICallBack<HumanityValueResponse>() {
            @Override
            public void onSuccess(HumanityValueResponse response) {
                if (response.getData() != null && response.getData().size() > 0) {
                    humanityValueAdapter.addItems(response.getData());
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
            }
        });
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
//                getViewBinding().setFundingResources(response);
                fundingResourceAdapter.addItems(response.getData());
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
