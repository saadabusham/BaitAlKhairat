package com.saad.baitalkhairat.ui.intro.casedetails;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCaseDetailsBinding;
import com.saad.baitalkhairat.model.errormodel.AddToCartError;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBackNew;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;

public class CaseDetailsViewModel extends BaseViewModel<CaseDetailsNavigator, FragmentCaseDetailsBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> CaseDetailsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (CaseDetailsNavigator) navigation, (FragmentCaseDetailsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        getViewBinding().setData(getNavigator().getCase());
    }

    public void onAddToCartClick() {
        getDataManager().getDonorsService().addToCart(getMyContext(), true,
                DeviceUtils.getUDID(getBaseActivity()), getNavigator().getCase().getId(),
                !getViewBinding().edAnotherAmount.getText().toString().isEmpty() ?
                        getViewBinding().edAnotherAmount.getText().toString() : getNavigator().getCase().getAmount()
                , new APICallBackNew<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.action_casesFragment_to_donorAppliedSuccessfulFragment);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        AddToCartError addToCartError = new Gson().fromJson(error, AddToCartError.class);
                        showToast(addToCartError.toString());
                    }

                    @Override
                    public void onNetworkError(String error, int errorCode) {
                        showToast(error);
                    }
                });
    }
}
