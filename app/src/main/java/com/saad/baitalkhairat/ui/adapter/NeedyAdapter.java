package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellNeedyBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.needs.Needy;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemNeedyViewModel;

import java.util.ArrayList;
import java.util.List;

public class NeedyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Needy> needyList;
    Context mContext;
    RecyclerClick mRecyclerClick;

    public NeedyAdapter(Context mContext, RecyclerClick mRecyclerClick) {
        this.needyList = new ArrayList<>();
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;
    }

    @Override
    public int getItemCount() {
        if (!needyList.isEmpty()) {
            return needyList.size();
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
        CellNeedyBinding cellBinding = CellNeedyBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NeedyCellViewHolder(cellBinding);
    }

    public void addItems(List<Needy> repoList) {
        needyList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(Needy needy) {
        needyList.add(needy);
        notifyDataSetChanged();
    }

    public void clearItems() {
        needyList.clear();
    }

    public void remove(int position) {
        needyList.remove(position);
    }

    public class NeedyCellViewHolder extends BaseViewHolder {

        private final CellNeedyBinding mBinding;

        public NeedyCellViewHolder(CellNeedyBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemNeedyViewModel(mContext, needyList.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setNeedy(needyList.get(position));
            }
        }

    }

}