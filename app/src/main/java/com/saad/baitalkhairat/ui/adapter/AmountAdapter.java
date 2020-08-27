package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellAmountBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemAmountViewModel;

import java.util.ArrayList;
import java.util.List;

public class AmountAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Amount> amountList;
    Context mContext;
    RecyclerClick mRecyclerClick;

    int selectedItemPosition;

    public Amount getSelectedItem() {
        return amountList.get(selectedItemPosition);
    }

    public AmountAdapter(Context mContext, RecyclerClick mRecyclerClick) {
        this.amountList = new ArrayList<>();
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;

    }

    @Override
    public int getItemCount() {
        if (!amountList.isEmpty()) {
            return amountList.size();
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
        CellAmountBinding cellBinding = CellAmountBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AmountCellViewHolder(cellBinding);
    }

    public void addItems(List<Amount> repoList) {
        amountList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(Amount amount) {
        amountList.add(amount);
        notifyDataSetChanged();
    }

    public Amount getItem(int position) {
        return amountList.get(position);
    }

    public void clearItems() {
        amountList.clear();
    }

    public void remove(int position) {
        amountList.remove(position);
    }

    public class AmountCellViewHolder extends BaseViewHolder {

        private final CellAmountBinding mBinding;

        public AmountCellViewHolder(CellAmountBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemAmountViewModel(mContext, amountList.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setAmount(amountList.get(position));
            }

            mBinding.linearAmount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    amountList.get(selectedItemPosition).setSelected(false);
                    amountList.get(position).setSelected(true);
                    selectedItemPosition = position;
                    notifyDataSetChanged();
                }
            });
        }

    }

}