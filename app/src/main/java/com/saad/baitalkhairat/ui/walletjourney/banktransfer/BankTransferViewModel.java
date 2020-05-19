package com.saad.baitalkhairat.ui.walletjourney.banktransfer;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;

import com.saad.baitalkhairat.databinding.FragmentBankTransferBinding;
import com.saad.baitalkhairat.model.DataExample;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.DeviceUtils;

import java.util.List;

public class BankTransferViewModel extends BaseViewModel<BankTransferNavigator, FragmentBankTransferBinding> {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<List<DataExample>> dataExampleList;

    public <V extends ViewDataBinding, N extends BaseNavigator> BankTransferViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (BankTransferNavigator) navigation, (FragmentBankTransferBinding) viewDataBinding);
        isLoading = new MutableLiveData<>();
        dataExampleList = new MutableLiveData<>();
    }


    @Override
    protected void setUp() {
        getViewBinding().tvAmount.setText(getNavigator().getAmount().getAmountFormatted());
    }

    public void onCopyClick() {
        DeviceUtils.copyToClipboard(getMyContext(), getViewBinding().tvAccountNumber.getText().toString());
    }

    public void onCopyInformationClick() {

    }

}
