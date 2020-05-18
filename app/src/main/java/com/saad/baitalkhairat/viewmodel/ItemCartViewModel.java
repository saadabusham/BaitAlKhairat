
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Cart;

public class ItemCartViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Cart cart;
    private int position;

    public ItemCartViewModel(Context context, Cart cart, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.cart = cart;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        notifyChange();
    }

    public void onRemoveClick(View view) {
        mRecyclerClick.onClick(cart, position);
    }


}
