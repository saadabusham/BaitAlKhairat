
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.account.AccountItem;


public class ItemAccountItemViewModel extends BaseObservable {

    private final Context context;
    RecyclerClickNoData mRecyclerClick;
    private AccountItem accountItem;
    private int position;

    public ItemAccountItemViewModel(Context context, AccountItem accountItem, int position, RecyclerClickNoData mRecyclerClick) {
        this.context = context;
        this.accountItem = accountItem;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public AccountItem getAccountItem() {
        return accountItem;
    }

    public void setAccountItem(AccountItem accountItem) {
        this.accountItem = accountItem;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(position);
    }
}
