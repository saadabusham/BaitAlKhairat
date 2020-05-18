
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Amount;


public class ItemAmountViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Amount amount;
    private int position;

    public ItemAmountViewModel(Context context, Amount amount, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.amount = amount;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(amount, position);
    }

    public String getAmountFormatted() {
        return amount.getAmount() + " " + amount.getCurrency();
    }

    public int getBackground() {
        return getAmount().isSelected() ?
                R.drawable.background_with_shadow_black :
                R.drawable.background_with_shadow;
    }

    public int getTextColor() {
        return getAmount().isSelected() ?
                R.color.white :
                R.color.black;
    }
}
