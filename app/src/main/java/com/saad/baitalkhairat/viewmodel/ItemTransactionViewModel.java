
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Transaction;

public class ItemTransactionViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Transaction transaction;
    private int position;

    public ItemTransactionViewModel(Context context, Transaction Transaction, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.transaction = Transaction;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        notifyChange();
    }

    public void onRemoveClick(View view) {
        mRecyclerClick.onClick(transaction, position);
    }


}
