
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.needs.Needy;


public class ItemNeedyViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Needy needy;
    private int position;

    public ItemNeedyViewModel(Context context, Needy needy, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.needy = needy;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Needy getNeedy() {
        return needy;
    }

    public void setNeedy(Needy needy) {
        this.needy = needy;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(needy, position);
    }
}
