package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellCategoryBinding;
import com.saad.baitalkhairat.databinding.CellLoadMoreGridBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Category;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private final List<Category> categoryList;
    Context mContext;
    RecyclerClick mRecyclerClick;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener loadMoreListener;

    public CategoryAdapter(Context mContext, RecyclerClick mRecyclerClick, RecyclerView recyclerView) {
        this.categoryList = new ArrayList<>();
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
        if (!categoryList.isEmpty()) {
            return categoryList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return categoryList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            CellCategoryBinding cellBinding = CellCategoryBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CategoryCellViewHolder(cellBinding);
        } else {
            CellLoadMoreGridBinding cellLoadMoreBinding = CellLoadMoreGridBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ProgressCellViewHolder(cellLoadMoreBinding);
        }
    }

    public void addItems(List<Category> repoList) {
        categoryList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(Category category) {
        categoryList.add(category);
        notifyDataSetChanged();
    }

    public void clearItems() {
        categoryList.clear();
    }

    public void remove(int position) {
        categoryList.remove(position);
    }

    public class CategoryCellViewHolder extends BaseViewHolder {

        private final CellCategoryBinding mBinding;

        public CategoryCellViewHolder(CellCategoryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemCategoryViewModel(mContext, categoryList.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setCategory(categoryList.get(position));
            }
        }

    }

    public class ProgressCellViewHolder extends BaseViewHolder {

        private final CellLoadMoreGridBinding mBinding;

        public ProgressCellViewHolder(CellLoadMoreGridBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mBinding.progressBar.setIndeterminate(true);
        }
    }

}