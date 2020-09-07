package com.saad.baitalkhairat.ui.profilejourney.viewdocument;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentViewDocumentBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.helper.AdapterBinding;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.File;
import com.saad.baitalkhairat.model.account.ProfileResponse;
import com.saad.baitalkhairat.model.account.UserResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.AppConstants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.saad.baitalkhairat.ui.profilejourney.identification_document.IdentificationDocumentViewModel.REMOVE;

public class ViewDocumentViewModel extends BaseViewModel<ViewDocumentNavigator, FragmentViewDocumentBinding> {

    File identificationDocument;
    String bindingKey = "";
    boolean isRemoved = false;

    public <V extends ViewDataBinding, N extends BaseNavigator> ViewDocumentViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ViewDocumentNavigator) navigation, (FragmentViewDocumentBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        bindingKey = GeneralFunction.generateUUID();
        identificationDocument = getNavigator().getDocument();
        AdapterBinding.setImageUrlWithHeader(getViewBinding().imgDocument, identificationDocument.getPath());
    }

    public File getIdentificationDocument() {
        return identificationDocument;
    }

    public void onBackClick() {
        popUp();
    }

    /**
     * todo
     */
    public void onRemoveClick() {
        new OnLineDialog(getMyContext()) {
            @Override
            public void onPositiveButtonClicked() {
                dismiss();
                removeDocument();
            }

            @Override
            public void onNegativeButtonClicked() {
                dismiss();
            }
        }.showConfirmationDialog(DialogTypes.OK_CANCEL,
                getMyContext().getResources().getString(R.string.remove),
                getMyContext().getResources().getString(R.string.are_you_sure));
    }

    private void removeDocument() {
        getDataManager().getAuthService().removeDocument(getMyContext(), true,
                identificationDocument.getId(), bindingKey, new APICallBack<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        getNavigator().getUser().getDeletedDocuments().clear();
                        getNavigator().getUser().getDeletedDocuments().add(String.valueOf(identificationDocument.getId()));
                        updateClicked();
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showErrorSnackBar(error);
                    }
                });
    }

    private UserResponse getUserObj() {
        getNavigator().getUser().setBinding_key(bindingKey);
        return getNavigator().getUser();
    }

    public void updateClicked() {
        getDataManager().getAuthService().getDataApi().updateProfile(getUserObj())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<ProfileResponse>(getMyContext(), true, new APICallBack<ProfileResponse>() {
                    @Override
                    public void onSuccess(ProfileResponse response) {
                        isRemoved = true;
                        popUp();
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showErrorSnackBar(error);
                    }
                }));
    }

    public void returnData() {
        if (isRemoved) {
            Intent data = new Intent();
            data.putExtra(AppConstants.BundleData.FILE, identificationDocument);
            ((MainActivity) getBaseActivity()).onActivityResultFromFragment(
                    REMOVE, Activity.RESULT_OK, data);
        }
    }
}
