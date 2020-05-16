package com.saad.baitalkhairat.ui.notificationjourney.notificationdetails;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentNotificationDetailsBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class NotificationDetailsViewModel extends BaseViewModel<NotificationDetailsNavigator, FragmentNotificationDetailsBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> NotificationDetailsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (NotificationDetailsNavigator) navigation, (FragmentNotificationDetailsBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {

    }
}
