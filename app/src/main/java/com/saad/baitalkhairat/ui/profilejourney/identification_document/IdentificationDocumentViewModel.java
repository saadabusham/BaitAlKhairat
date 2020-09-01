package com.saad.baitalkhairat.ui.profilejourney.identification_document;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentIdentificationDocumentBinding;
import com.saad.baitalkhairat.enums.PickImageTypes;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.model.File;
import com.saad.baitalkhairat.model.account.ProfileResponse;
import com.saad.baitalkhairat.model.account.UserResponse;
import com.saad.baitalkhairat.model.needs.AddNeedDocResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.adapter.IdentificationDocumentAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.CustomUploadingDialog;
import com.saad.baitalkhairat.ui.dialog.PickImageFragmentDialog;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.PickImageUtility;
import com.saad.baitalkhairat.utils.ProgressRequestBody;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IdentificationDocumentViewModel extends BaseViewModel<IdentificationDocumentNavigator,
        FragmentIdentificationDocumentBinding>
        implements ProgressRequestBody.UploadCallbacks, RecycleDeleteClick<File> {

    CustomUploadingDialog customUploadingDialog;

    IdentificationDocumentAdapter identificationDocumentAdapter;
    String bindingKey = "";

    public <V extends ViewDataBinding, N extends BaseNavigator> IdentificationDocumentViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (IdentificationDocumentNavigator) navigation, (FragmentIdentificationDocumentBinding) viewDataBinding);
    }


    @Override
    protected void setUp() {
        bindingKey = GeneralFunction.generateUUID();
        customUploadingDialog = new CustomUploadingDialog(getMyContext());
        setUpRecycler();
    }


    private void setUpRecycler() {
        getViewBinding().recyclerView.setLayoutManager(new GridLayoutManager(getMyContext(), 2));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getMyContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getMyContext(), R.drawable.divider));
        getViewBinding().recyclerView.addItemDecoration(itemDecorator);
        identificationDocumentAdapter = new IdentificationDocumentAdapter(getMyContext(), this);
        getViewBinding().recyclerView.setAdapter(identificationDocumentAdapter);
        if (getNavigator().getUser().getDocuments() != null && getNavigator().getUser().getDocuments().size() > 0) {
            identificationDocumentAdapter.addItems(getNavigator().getUser().getDocuments());
        }
    }

    public void updatePictureClick() {
        PickImageFragmentDialog pickImageFragmentDialog = new PickImageFragmentDialog.Builder().build();
        pickImageFragmentDialog.setMethodCallBack(new PickImageFragmentDialog.methodClick() {
            @Override
            public void onMethodBack(int type) {
                if (type == PickImageTypes.GALLERY.getIntValue()) {
                    PickImageUtility.selectImage(getBaseActivity());
                } else {
                    PickImageUtility.TakePictureIntent(getBaseActivity());
                }
            }
        });
        pickImageFragmentDialog.show(getBaseActivity().getSupportFragmentManager(), "picker");
    }

    @Override
    public void onProgressUpdate(int percentage) {
        customUploadingDialog.setProgress(percentage);
    }

    @Override
    public void onError() {
        customUploadingDialog.setProgress(100);
    }

    @Override
    public void onFinish() {
        customUploadingDialog.setProgress(100);
    }


    public void addImage(String imagePath) {
        identificationDocumentAdapter.addItem(new File(imagePath));
        getViewBinding().recyclerView.scrollToPosition(identificationDocumentAdapter.getItemCount() - 1);
        uploadProfilePicture(imagePath);
    }

    public void uploadProfilePicture(String path) {
        customUploadingDialog.showProgress();
        getDataManager().getAuthService().getDataApi()
                .addAttachment(GeneralFunction.getImageMultiPartWithProgress(path,
                        "attachment", this), bindingKey)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<AddNeedDocResponse>(getMyContext(), false,
                        new APICallBack<AddNeedDocResponse>() {
                            @Override
                            public void onSuccess(AddNeedDocResponse response) {
                                customUploadingDialog.setProgress(100);
                                getNavigator().getUser().getDocuments().add(response.getFile());
                                identificationDocumentAdapter.replaceLastItem(response.getFile());
                                updateClicked();
                            }

                            @Override
                            public void onError(String error, int errorCode) {
                                showSnackBar(getMyContext().getString(R.string.error),
                                        error, getMyContext().getResources().getString(R.string.OK),
                                        new SnackViewBulider.SnackbarCallback() {
                                            @Override
                                            public void onActionClick(Snackbar snackbar) {
                                                snackbar.dismiss();
                                            }
                                        });
                                customUploadingDialog.setProgress(100);
                            }
                        }));

    }

    public void updateClicked() {
        getDataManager().getAuthService().getDataApi().updateProfile(getUserObj())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<ProfileResponse>(getMyContext(), true, new APICallBack<ProfileResponse>() {
                    @Override
                    public void onSuccess(ProfileResponse response) {
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                    }
                }));
    }

    private UserResponse getUserObj() {
        getNavigator().getUser().setBinding_key(bindingKey);
        return getNavigator().getUser();
    }

    @Override
    public void onClick(boolean isDelete, File object, int position) {
        if (isDelete) {
            removeDocument(object.getId(), position);
        } else {
            Bundle data = new Bundle();
            data.putSerializable(AppConstants.BundleData.DOCUMENT, object);
            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_identificationDocumentFragment_to_viewDocumentFragment, data);
        }
    }

    private void removeDocument(int id, int position) {
        getDataManager().getAuthService().removeDocument(getMyContext(), true,
                id, bindingKey, new APICallBack<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        identificationDocumentAdapter.remove(position);
                        identificationDocumentAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showErrorSnackBar(error);
                    }
                });
    }
}
