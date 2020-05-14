package com.saad.baitalkhairat.ui.adapter;//package com.saad.baitalkhairat.ui.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//import com.saad.baitalkhairat.databinding.CellHomeBinding;
//import com.saad.baitalkhairat.helper.GeneralFunction;
//import com.saad.baitalkhairat.interfaces.RecyclerClick;
//import com.saad.baitalkhairat.ui.base.BaseViewHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
//
//    private final int VIEW_ITEM = 1;
//    private final List<Category> mCategoryList;
//
//    Context mContext;
//    RecyclerClick mRecyclerClick;
//
//    public HomeAdapter(Context mContext, RecyclerClick mRecyclerClick) {
//        this.mCategoryList = new ArrayList<>();
//        this.mContext = mContext;
//        this.mRecyclerClick = mRecyclerClick;
//    }
//
//
//    @Override
//    public int getItemCount() {
//        if (!mCategoryList.isEmpty()) {
//            return mCategoryList.size();
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        holder.onBind(position);
//    }
//
//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        CellHomeBinding cellHomeBinding = CellHomeBinding
//                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new HomeCellViewHolder(cellHomeBinding);
//    }
//
//    public void addItems(List<Category> repoList) {
//        mCategoryList.addAll(repoList);
//    }
//
//    public void addItem(Category category) {
//        mCategoryList.add(category);
//        notifyDataSetChanged();
//    }
//
//    public void clearItems() {
//        mCategoryList.clear();
//    }
//
//    public void remove(int position) {
//        mCategoryList.remove(position);
//    }
//
//    public class HomeCellViewHolder extends BaseViewHolder {
//
//        private final CellHomeBinding mBinding;
//
//        public HomeCellViewHolder(CellHomeBinding binding) {
//            super(binding.getRoot());
//            this.mBinding = binding;
//        }
//
//        @Override
//        public void onBind(int position) {
//            this.mBinding.tvName.setText(mCategoryList.get(position).getName());
//            GeneralFunction.loadImage(mContext, mCategoryList.get(position).getImage(), this.mBinding.imgPicture);
//            this.mBinding.cardFavoriteCell.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mRecyclerClick.onClick(mCategoryList.get(position), position);
//                }
//            });
//        }
//
//    }
//
//
//}