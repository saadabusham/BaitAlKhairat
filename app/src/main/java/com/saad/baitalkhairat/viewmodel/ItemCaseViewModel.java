
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.enums.RecycleClickCasesTypes;
import com.saad.baitalkhairat.interfaces.RecyclerClickWithCase;
import com.saad.baitalkhairat.model.cases.Case;


public class ItemCaseViewModel extends BaseObservable {

    private final Context context;
    RecyclerClickWithCase mRecyclerClick;
    private Case caseItem;
    private int position;

    public ItemCaseViewModel(Context context, Case caseItem, int position, RecyclerClickWithCase mRecyclerClick) {
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
        mRecyclerClick.onClick(caseItem, position, RecycleClickCasesTypes.DETAILS.getType());
    }

    public void onAddToCartClick(View view) {
        mRecyclerClick.onClick(caseItem, position, RecycleClickCasesTypes.ADD_TO_CART.getType());
    }

    public void onDonateClick(View view) {
        mRecyclerClick.onClick(caseItem, position, RecycleClickCasesTypes.DONATE.getType());
    }

}
