package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellDocumentBinding;
import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.model.File;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemIdentificationDocumentViewModel;

import java.util.ArrayList;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private final List<File> documentsList;
    Context mContext;
    RecycleDeleteClick mRecyclerClick;

    public DocumentAdapter(Context mContext, RecycleDeleteClick mRecyclerClick) {
        this.documentsList = new ArrayList<>();
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;
    }


    @Override
    public int getItemCount() {
        if (!documentsList.isEmpty()) {
            return documentsList.size();
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
        CellDocumentBinding cellBinding = CellDocumentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CellViewHolder(cellBinding);
    }

    public void addItems(List<File> repoList) {
        documentsList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(File identificationPaper) {
        documentsList.add(identificationPaper);
        notifyDataSetChanged();
    }

    public void clearItems() {
        documentsList.clear();
    }

    public void remove(int position) {
        documentsList.remove(position);
    }

    public File getItem(int position) {
        return documentsList.get(position);
    }

    public class CellViewHolder extends BaseViewHolder {

        private final CellDocumentBinding mBinding;

        public CellViewHolder(CellDocumentBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemIdentificationDocumentViewModel(mContext, documentsList.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setIdentificationDocument(documentsList.get(position));
            }
        }

    }
}