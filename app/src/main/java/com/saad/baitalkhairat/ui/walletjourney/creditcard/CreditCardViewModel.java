package com.saad.baitalkhairat.ui.walletjourney.creditcard;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;

import com.saad.baitalkhairat.databinding.FragmentCreditCardBinding;
import com.saad.baitalkhairat.model.DataExample;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

import java.util.List;

public class CreditCardViewModel extends BaseViewModel<CreditCardNavigator, FragmentCreditCardBinding> {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<List<DataExample>> dataExampleList;

    public <V extends ViewDataBinding, N extends BaseNavigator> CreditCardViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (CreditCardNavigator) navigation, (FragmentCreditCardBinding) viewDataBinding);
        isLoading = new MutableLiveData<>();
        dataExampleList = new MutableLiveData<>();
    }


    @Override
    protected void setUp() {
        getViewBinding().tvAmount.setText(getNavigator().getAmount().getAmountFormatted());
    }

    public void onPayClicked() {

    }

}
