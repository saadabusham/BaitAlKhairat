
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;


public class ItemMyInfoViewModel extends BaseObservable {

    private final Context context;
    RecyclerClickNoData mRecyclerClick;
    private int text;
    private int position;

    public ItemMyInfoViewModel(Context context, int text, int position, RecyclerClickNoData mRecyclerClick) {
        this.context = context;
        this.text = text;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(position);
    }


}
