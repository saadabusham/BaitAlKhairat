package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellCaseGridBinding;
import com.saad.baitalkhairat.databinding.CellLoadMoreCaseGridBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Case;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemCaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class CaseGridAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private final List<Case> caseList;
    Context mContext;
    RecyclerClick mRecyclerClick;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener loadMoreListener;

    public CaseGridAdapter(Context mContext, ArrayList<Case> caseList, RecyclerClick mRecyclerClick, RecyclerView recyclerView) {
        this.caseList = caseList;
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView,
                                       int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    if (!loading && totalItemCount - 1 == (lastVisibleItem)) {
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.loadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        if (!caseList.isEmpty()) {
            return caseList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return caseList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            CellCaseGridBinding cellBinding = CellCaseGridBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CaseGridCellViewHolder(cellBinding);
        } else {
            CellLoadMoreCaseGridBinding cellLoadMoreBinding = CellLoadMoreCaseGridBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ProgressCellViewHolder(cellLoadMoreBinding);
        }
    }

    public void addItems(List<Case> repoList) {
        caseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(Case caseItem) {
        caseList.add(caseItem);
        notifyDataSetChanged();
    }

    public void clearItems() {
        caseList.clear();
    }

    public void remove(int position) {
        caseList.remove(position);
    }

    public class CaseGridCellViewHolder extends BaseViewHolder {

        private final CellCaseGridBinding mBinding;

        public CaseGridCellViewHolder(CellCaseGridBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemCaseViewModel(mContext, caseList.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setCaseItem(caseList.get(position));
            }
        }

    }

    public class ProgressCellViewHolder extends BaseViewHolder {

        private final CellLoadMoreCaseGridBinding mBinding;

        public ProgressCellViewHolder(CellLoadMoreCaseGridBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mBinding.progressBar.setIndeterminate(true);
        }
    }

}