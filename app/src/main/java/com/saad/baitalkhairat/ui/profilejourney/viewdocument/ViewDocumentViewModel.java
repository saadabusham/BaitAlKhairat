package com.saad.baitalkhairat.ui.profilejourney.viewdocument;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentViewDocumentBinding;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.IdentificationDocument;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class ViewDocumentViewModel extends BaseViewModel<ViewDocumentNavigator, FragmentViewDocumentBinding> {

    IdentificationDocument identificationDocument;

    public <V extends ViewDataBinding, N extends BaseNavigator> ViewDocumentViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ViewDocumentNavigator) navigation, (FragmentViewDocumentBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        identificationDocument = getNavigator().getDocument();
        GeneralFunction.loadImage(getMyContext(), identificationDocument.getImage(), getViewBinding().imgDocument);
    }

    public IdentificationDocument getIdentificationDocument() {
        return identificationDocument;
    }

    public void onBackClick() {
        popUp();
    }

    public void onRemoveClick() {

    }
}