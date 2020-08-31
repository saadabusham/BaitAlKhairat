package com.saad.baitalkhairat.ui.newsjourney.newsdetails;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentNewsDetailsBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class NewsDetailsViewModel extends BaseViewModel<NewsDetailsNavigator, FragmentNewsDetailsBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> NewsDetailsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (NewsDetailsNavigator) navigation, (FragmentNewsDetailsBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        getViewBinding().setData(getNavigator().getNews());
    }
}
