package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellFundingResourceBinding;
import com.saad.baitalkhairat.model.app.aboutus.FundingResource;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemFundingResourceViewModel;

import java.util.ArrayList;
import java.util.List;

public class FundingResourceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<FundingResource> fundingResources;
    Context mContext;


    public FundingResourceAdapter(Context mContext) {
        this.fundingResources = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        if (!fundingResources.isEmpty()) {
            return fundingResources.size();
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
        CellFundingResourceBinding cellBinding = CellFundingResourceBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FundingResourceCellViewHolder(cellBinding);
    }

    public void addItems(List<FundingResource> repoList) {
        fundingResources.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(FundingResource fundingResource) {
        fundingResources.add(fundingResource);
        notifyDataSetChanged();
    }

    public FundingResource getItem(int position) {
        return fundingResources.get(position);
    }

    public void clearItems() {
        fundingResources.clear();
    }

    public void remove(int position) {
        fundingResources.remove(position);
    }

    public class FundingResourceCellViewHolder extends BaseViewHolder {

        private final CellFundingResourceBinding mBinding;

        public FundingResourceCellViewHolder(CellFundingResourceBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemFundingResourceViewModel(mContext, fundingResources.get(position), position));
            } else {
                mBinding.getViewModel().setFundingResource(fundingResources.get(position), position);
            }
        }

    }

}