package com.saad.baitalkhairat.ui.profilejourney.editprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentEditProfileBinding;
import com.saad.baitalkhairat.helper.GeneralFunction;
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


public class EditProfileFragment extends BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>
        implements EditProfileNavigator, ActivityResultCallBack {

    private static final String TAG = EditProfileFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private EditProfileViewModel mEditProfileViewModel;
    private FragmentEditProfileBinding mViewBinding;

    public static EditProfileFragment newInstance() {
        Bundle args = new Bundle();
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        return R.layout.fragment_edit_profile;
    }

    @Override
    public EditProfileViewModel getViewModel() {
        mEditProfileViewModel = (EditProfileViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(EditProfileViewModel.class, getViewDataBinding(), this);
        return mEditProfileViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, getMyContext().getString(R.string.edit_profile));
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
//                    mEditProfileViewModel.uploadProfilePicture(result.getUri());
//                    mViewBinding.imgPicture.setImageURI(result.getUri());
                    GeneralFunction.loadImage(getMyContext(), result.getUri().toString(), mViewBinding.imgPicture);

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
