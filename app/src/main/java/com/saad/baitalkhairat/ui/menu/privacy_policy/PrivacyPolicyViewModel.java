package com.saad.baitalkhairat.ui.menu.privacy_policy;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentPrivacyPolicyBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;


public class PrivacyPolicyViewModel extends BaseViewModel<PrivacyPolicyNavigator, FragmentPrivacyPolicyBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> PrivacyPolicyViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (PrivacyPolicyNavigator) navigation, (FragmentPrivacyPolicyBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {

    }
}
