
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.model.IdentificationDocument;


public class ItemIdentificationDocumentViewModel extends BaseObservable {

    private final Context context;
    RecycleDeleteClick mRecyclerClick;
    private IdentificationDocument identificationDocument;
    private int position;

    public ItemIdentificationDocumentViewModel(Context context, IdentificationDocument identificationPaper, int position, RecycleDeleteClick mRecyclerClick) {
        this.context = context;
        this.identificationDocument = identificationPaper;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public IdentificationDocument getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(IdentificationDocument identificationDocument) {
        this.identificationDocument = identificationDocument;
        notifyChange();
    }

    public String getImage() {
        return identificationDocument.getImage();
    }

    public void onItemClick(boolean isRemove) {
        mRecyclerClick.onClick(isRemove, identificationDocument, position);
    }

}
