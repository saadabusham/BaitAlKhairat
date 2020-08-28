
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.databinding.CellCaseGridBinding;
import com.saad.baitalkhairat.databinding.CellCaseListBinding;
import com.saad.baitalkhairat.enums.RecycleClickCasesTypes;
import com.saad.baitalkhairat.interfaces.RecyclerClickWithCase;
import com.saad.baitalkhairat.model.cases.Case;


public class ItemCaseViewModel extends BaseObservable {

    CellCaseGridBinding cellCaseGridBinding;
    RecyclerClickWithCase mRecyclerClick;
    private Case caseItem;
    private int position;
    CellCaseListBinding cellCaseListBinding;
    private Context context;

    public ItemCaseViewModel(Context context, Case caseItem, int position, RecyclerClickWithCase mRecyclerClick, CellCaseGridBinding mBinding) {
        this.context = context;
        this.caseItem = caseItem;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
        this.cellCaseGridBinding = mBinding;
    }

    public ItemCaseViewModel(Context mContext, Case caseItem, int position, RecyclerClickWithCase mRecyclerClick, CellCaseListBinding mBinding) {
        this.context = mContext;
        this.caseItem = caseItem;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
        this.cellCaseListBinding = mBinding;
    }


    public Case getCaseItem() {
        return caseItem;
    }

    public void setCaseItem(Case caseItem) {
        this.caseItem = caseItem;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(caseItem, position, RecycleClickCasesTypes.DETAILS.getType(), "0");
    }

    public void onAddToCartClick(View view) {
        mRecyclerClick.onClick(caseItem, position, RecycleClickCasesTypes.ADD_TO_CART.getType(),
                cellCaseGridBinding != null ?
                        cellCaseGridBinding.tvAnotherAmount.getText().toString() :
                        cellCaseListBinding.tvAnotherAmount.getText().toString());
    }


}
