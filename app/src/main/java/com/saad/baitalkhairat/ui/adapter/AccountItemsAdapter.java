package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellAccountItemsBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.account.AccountItem;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemAccountItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class AccountItemsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    Context mContext;
    RecyclerClickNoData mRecyclerClick;
    private ArrayList<AccountItem> accountItems;

    public AccountItemsAdapter(Context mContext, ArrayList<AccountItem> accountItems, RecyclerClickNoData mRecyclerClick) {
        this.mContext = mContext;
        this.accountItems = new ArrayList<>(accountItems);
        this.mRecyclerClick = mRecyclerClick;
    }

    @Override
    public int getItemCount() {
        if (!accountItems.isEmpty()) {
            return accountItems.size();
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
        CellAccountItemsBinding cellSettingBinding = CellAccountItemsBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AccountItemViewHolder(cellSettingBinding);
    }

    public void addItems(List<AccountItem> menuItems) {
        accountItems.addAll(menuItems);
    }


    public class AccountItemViewHolder extends BaseViewHolder {

        private final CellAccountItemsBinding mBinding;

        public AccountItemViewHolder(CellAccountItemsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemAccountItemViewModel(mContext, accountItems.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setAccountItem(accountItems.get(position));
            }
        }

    }

}