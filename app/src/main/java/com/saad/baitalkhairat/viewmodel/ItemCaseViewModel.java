
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Case;


public class ItemCaseViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Case caseItem;
    private int position;

    public ItemCaseViewModel(Context context, Case caseItem, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.caseItem = caseItem;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Case getCaseItem() {
        return caseItem;
    }

    public void setCaseItem(Case caseItem) {
        this.caseItem = caseItem;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(caseItem, position);
    }
}
