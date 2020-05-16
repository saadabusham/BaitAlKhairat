package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellMyInfoBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemMyInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    ArrayList<Integer> arrayListText;
    Context mContext;
    RecyclerClickNoData mRecyclerClick;

    public MyInfoAdapter(Context mContext, RecyclerClickNoData mRecyclerClick, List<Integer> text) {
        this.arrayListText = new ArrayList<>(text);
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;
    }

    @Override
    public int getItemCount() {
        if (!arrayListText.isEmpty()) {
            return arrayListText.size();
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
        CellMyInfoBinding cellSettingBinding = CellMyInfoBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyInfoItemViewHolder(cellSettingBinding);
    }

    public void addItems(List<Integer> text) {
        arrayListText.addAll(text);
    }


    public class MyInfoItemViewHolder extends BaseViewHolder {

        private final CellMyInfoBinding mBinding;

        public MyInfoItemViewHolder(CellMyInfoBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemMyInfoViewModel(mContext, arrayListText.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setText(arrayListText.get(position));
            }
        }

    }

}