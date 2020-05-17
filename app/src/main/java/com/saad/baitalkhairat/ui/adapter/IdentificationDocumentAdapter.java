package com.saad.baitalkhairat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saad.baitalkhairat.databinding.CellIdentifecationDocumentBinding;
import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.model.IdentificationDocument;
import com.saad.baitalkhairat.ui.base.BaseViewHolder;
import com.saad.baitalkhairat.viewmodel.ItemIdentificationDocumentViewModel;

import java.util.ArrayList;
import java.util.List;

public class IdentificationDocumentAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private final List<IdentificationDocument> identificationDocuments;
    Context mContext;
    RecycleDeleteClick mRecyclerClick;

    public IdentificationDocumentAdapter(Context mContext, RecycleDeleteClick mRecyclerClick) {
        this.identificationDocuments = new ArrayList<>();
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;

    }


    @Override
    public int getItemCount() {
        if (!identificationDocuments.isEmpty()) {
            return identificationDocuments.size();
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
        CellIdentifecationDocumentBinding cellBinding = CellIdentifecationDocumentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CellViewHolder(cellBinding);
    }

    public void addItems(List<IdentificationDocument> repoList) {
        identificationDocuments.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(IdentificationDocument identificationPaper) {
        identificationDocuments.add(identificationPaper);
        notifyDataSetChanged();
    }

    public void clearItems() {
        identificationDocuments.clear();
    }

    public void remove(int position) {
        identificationDocuments.remove(position);
    }

    public class CellViewHolder extends BaseViewHolder {

        private final CellIdentifecationDocumentBinding mBinding;

        public CellViewHolder(CellIdentifecationDocumentBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemIdentificationDocumentViewModel(mContext, identificationDocuments.get(position), position, mRecyclerClick));
            } else {
                mBinding.getViewModel().setIdentificationDocument(identificationDocuments.get(position));
            }
        }

    }
}