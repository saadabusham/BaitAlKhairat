
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.model.app.aboutus.FundingResource;

public class ItemFundingResourceViewModel extends BaseObservable {

    private final Context context;
    private FundingResource fundingResource;
    private int position;

    public ItemFundingResourceViewModel(Context context, FundingResource fundingResource, int position) {
        this.context = context;
        this.fundingResource = fundingResource;
        this.position = position;
    }

    public FundingResource getFundingResource() {
        return fundingResource;
    }

    public void setFundingResource(FundingResource cart, int position) {
        this.fundingResource = cart;
        this.position = position;
        notifyChange();
    }
}
