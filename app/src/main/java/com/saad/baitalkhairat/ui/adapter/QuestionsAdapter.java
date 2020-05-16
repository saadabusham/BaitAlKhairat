package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellCommonQuastionsBinding;
import com.saad.baitalkhairat.databinding.CellLoadMoreBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Question;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemQuestionViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private final List<Question> questionList;
    Context mContext;
    RecyclerClick mRecyclerClick;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener loadMoreListener;

    public QuestionsAdapter(Context mContext, RecyclerClick mRecyclerClick, RecyclerView recyclerView) {
        this.questionList = new ArrayList<>();
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
        if (!questionList.isEmpty()) {
            return questionList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return questionList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            CellCommonQuastionsBinding cellBinding = CellCommonQuastionsBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new QuestionCellViewHolder(cellBinding);
        } else {
            CellLoadMoreBinding cellLoadMoreBinding = CellLoadMoreBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ProgressCellViewHolder(cellLoadMoreBinding);
        }
    }

    public void addItems(List<Question> repoList) {
        questionList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(Question question) {
        questionList.add(question);
        notifyDataSetChanged();
    }

    public void clearItems() {
        questionList.clear();
    }

    public void remove(int position) {
        questionList.remove(position);
    }

    public class QuestionCellViewHolder extends BaseViewHolder {

        private final CellCommonQuastionsBinding mBinding;

        public QuestionCellViewHolder(CellCommonQuastionsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemQuestionViewModel(mContext, questionList.get(position), position, mBinding));
            } else {
                mBinding.getViewModel().setQuestion(questionList.get(position));
            }
        }

    }

    public class ProgressCellViewHolder extends BaseViewHolder {

        private final CellLoadMoreBinding mBinding;

        public ProgressCellViewHolder(CellLoadMoreBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mBinding.progressBar.setIndeterminate(true);
        }
    }

}