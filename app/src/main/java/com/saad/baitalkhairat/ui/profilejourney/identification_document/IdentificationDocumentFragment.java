package com.saad.baitalkhairat.ui.profilejourney.identification_document;

import android.content.Context;
import android.content.Intent;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentIdentificationDocumentBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.user.UserResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.PickImageUtility;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;
import com.theartofdev.edmodo.cropper.CropImage;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


public class IdentificationDocumentFragment extends BaseFragment<FragmentIdentificationDocumentBinding, IdentificationDocumentViewModel>
        implements IdentificationDocumentNavigator, ActivityResultCallBack {

    private static final String TAG = IdentificationDocumentFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private IdentificationDocumentViewModel mEditProfileViewModel;
    private FragmentIdentificationDocumentBinding mViewBinding;

    @Override
    public boolean hasOptionMenu() {
        return false;
    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public boolean hideToolbar() {
        return true;
    }

    @Override
    public boolean hideBottomSheet() {
        return true;
    }

    @Override
    public boolean isNeedActivityResult() {
        return true;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return this::callBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_identification_document;
    }

    @Override
    public IdentificationDocumentViewModel getViewModel() {
        mEditProfileViewModel = (IdentificationDocumentViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(IdentificationDocumentViewModel.class, getViewDataBinding(), this);
        return mEditProfileViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mEditProfileViewModel.setUp();
        setUpToolbar(mViewBinding.toolbar, TAG, getMyContext().getString(R.string.my_identification_papers));
        mEditProfileViewModel.setUp();

    }

    @Override
    public void callBack(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PickImageUtility.PICK_IMAGE_GALLERY:
                if (resultCode == RESULT_OK) {
                    PickImageUtility.Crop(data.getData(), getBaseActivity(), true);
                }
                break;
            case PickImageUtility.PICK_IMAGE_CAMERA:
                if (resultCode == RESULT_OK) {
                    PickImageUtility.Crop(PickImageUtility.getCameraImage(), getBaseActivity(), true);
                }
                break;
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE: {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    mEditProfileViewModel.addImage(result.getUri().getPath());

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }
    }

    @Override
    public UserResponse getUser() {
        return (UserResponse) getArguments().getSerializable(AppConstants.BundleData.USER);
    }
}
