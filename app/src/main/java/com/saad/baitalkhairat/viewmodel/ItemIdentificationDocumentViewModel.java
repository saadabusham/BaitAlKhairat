
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.model.File;


public class ItemIdentificationDocumentViewModel extends BaseObservable {

    private final Context context;
    RecycleDeleteClick mRecyclerClick;
    private File identificationDocument;
    private int position;

    public ItemIdentificationDocumentViewModel(Context context, File identificationPaper, int position, RecycleDeleteClick mRecyclerClick) {
        this.context = context;
        this.identificationDocument = identificationPaper;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public File getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(File identificationDocument) {
        this.identificationDocument = identificationDocument;
        notifyChange();
    }

    public void onItemClick(boolean isRemove) {
        mRecyclerClick.onClick(isRemove, identificationDocument, position);
    }

}
