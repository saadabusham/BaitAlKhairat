package com.saad.baitalkhairat.ui.walletjourney.banktransfer;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentBankTransferBinding;
import com.saad.baitalkhairat.model.app.AppBank;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;

public class BankTransferViewModel extends BaseViewModel<BankTransferNavigator, FragmentBankTransferBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> BankTransferViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (BankTransferNavigator) navigation, (FragmentBankTransferBinding) viewDataBinding);

    }


    @Override
    protected void setUp() {
        getViewBinding().tvAmount.setText(getNavigator().getAmount().getAmountFormatted());
        getData();
    }

    private void getData() {
        getDataManager().getAppService().getAppBankInfo(getMyContext(), true, new APICallBack<AppBank>() {
            @Override
            public void onSuccess(AppBank response) {
                getViewBinding().setData(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    public void onCopyClick() {
        DeviceUtils.copyToClipboard(getMyContext(), getViewBinding().tvAccountNumber.getText().toString());
    }

    public void onCopyInformationClick() {

    }

}
