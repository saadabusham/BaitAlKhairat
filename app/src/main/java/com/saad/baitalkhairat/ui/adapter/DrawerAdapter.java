package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.CellDrawerItemBinding;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.MenuItem;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemDrawerViewModel;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    Context mContext;
    RecyclerClickNoData mRecyclerClick;
    private ArrayList<MenuItem> arrayListMenuItems;

    public DrawerAdapter(Context mContext, ArrayList<MenuItem> arrayListMenuItems, RecyclerClickNoData mRecyclerClick) {
        this.mContext = mContext;
        this.arrayListMenuItems = new ArrayList<>(arrayListMenuItems);
        this.mRecyclerClick = mRecyclerClick;
    }

    @Override
    public int getItemCount() {
        if (!arrayListMenuItems.isEmpty()) {
            return arrayListMenuItems.size();
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
        CellDrawerItemBinding cellSettingBinding = CellDrawerItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DrawerItemViewHolder(cellSettingBinding);
    }

    public void addItems(List<MenuItem> menuItems) {
        arrayListMenuItems.addAll(menuItems);
    }


    public class DrawerItemViewHolder extends BaseViewHolder {

        private final CellDrawerItemBinding mBinding;

        public DrawerItemViewHolder(CellDrawerItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemDrawerViewModel(mContext, arrayListMenuItems.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setMenuItem(arrayListMenuItems.get(position));
            }
            if (position == arrayListMenuItems.size() - 1) {
                GeneralFunction.tintImage(mBinding.imgIcon, R.color.red);
                mBinding.tvTitle.setTextColor(mContext.getResources().getColor(R.color.red));
            }
        }

    }

}