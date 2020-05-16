package com.saad.baitalkhairat.ui.intro.casedetails;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentCaseDetailsBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class CaseDetailsViewModel extends BaseViewModel<CaseDetailsNavigator, FragmentCaseDetailsBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> CaseDetailsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (CaseDetailsNavigator) navigation, (FragmentCaseDetailsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {

    }

    public void onAddToCartClick() {

    }

    public void onDonateClick() {

    }

}
