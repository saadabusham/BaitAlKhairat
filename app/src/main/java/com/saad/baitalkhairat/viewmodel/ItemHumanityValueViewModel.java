
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.model.app.aboutus.HumanityValue;


public class ItemHumanityValueViewModel extends BaseObservable {

    private final Context context;
    private HumanityValue humanityValue;
    private int position;

    public ItemHumanityValueViewModel(Context context, HumanityValue humanityValue, int position) {
        this.context = context;
        this.humanityValue = humanityValue;
        this.position = position;
    }

    public HumanityValue getHumanityValue() {
        return humanityValue;
    }

    public void setHumanityValue(HumanityValue humanityValue) {
        this.humanityValue = humanityValue;
        notifyChange();
    }

    public void onItemClick(View view) {
    }
}
