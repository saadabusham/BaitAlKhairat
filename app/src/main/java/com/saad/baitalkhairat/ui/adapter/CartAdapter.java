package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellCartBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Cart;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemCartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Cart> cartList;
    Context mContext;
    RecyclerClick mRecyclerClick;

    public CartAdapter(Context mContext, RecyclerClick mRecyclerClick, RecyclerView recyclerView) {
        this.cartList = new ArrayList<>();
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;
    }

    @Override
    public int getItemCount() {
        if (!cartList.isEmpty()) {
            return cartList.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CellCartBinding cellBinding = CellCartBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartCellViewHolder(cellBinding);
    }

    public void addItems(List<Cart> repoList) {
        cartList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(Cart cart) {
        cartList.add(cart);
        notifyDataSetChanged();
    }

    public void clearItems() {
        cartList.clear();
    }

    public void remove(int position) {
        cartList.remove(position);
    }

    public class CartCellViewHolder extends BaseViewHolder {

        private final CellCartBinding mBinding;

        public CartCellViewHolder(CellCartBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemCartViewModel(mContext, cartList.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setCart(cartList.get(position));
            }
        }

    }

}