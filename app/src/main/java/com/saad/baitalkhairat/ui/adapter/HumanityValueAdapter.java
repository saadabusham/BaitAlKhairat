package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellHumanityValueBinding;
import com.saad.baitalkhairat.model.app.aboutus.HumanityValue;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemHumanityValueViewModel;

import java.util.ArrayList;
import java.util.List;

public class HumanityValueAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<HumanityValue> humanityValueList;
    Context mContext;


    public HumanityValueAdapter(Context mContext) {
        this.humanityValueList = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        if (!humanityValueList.isEmpty()) {
            return humanityValueList.size();
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
        CellHumanityValueBinding cellBinding = CellHumanityValueBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HumanityValueCellViewHolder(cellBinding);
    }

    public void addItems(List<HumanityValue> repoList) {
        humanityValueList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(HumanityValue humanityValue) {
        humanityValueList.add(humanityValue);
        notifyDataSetChanged();
    }

    public HumanityValue getItem(int position) {
        return humanityValueList.get(position);
    }

    public void clearItems() {
        humanityValueList.clear();
    }

    public void remove(int position) {
        humanityValueList.remove(position);
    }

    public class HumanityValueCellViewHolder extends BaseViewHolder {

        private final CellHumanityValueBinding mBinding;

        public HumanityValueCellViewHolder(CellHumanityValueBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemHumanityValueViewModel(mContext, humanityValueList.get(position), position));
            } else {
                mBinding.getViewModel().setHumanityValue(humanityValueList.get(position));
            }
        }

    }

}