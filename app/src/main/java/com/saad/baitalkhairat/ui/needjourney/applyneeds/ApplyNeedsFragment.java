package com.saad.baitalkhairat.ui.needjourney.applyneeds;

import android.content.Context;
import android.content.Intent;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentApplyNeedsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.PickImageUtility;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;
import com.theartofdev.edmodo.cropper.CropImage;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


public class ApplyNeedsFragment extends BaseFragment<FragmentApplyNeedsBinding, ApplyNeedsViewModel>
        implements ApplyNeedsNavigator, ActivityResultCallBack {

    private static final String TAG = ApplyNeedsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ApplyNeedsViewModel mViewModel;
    private FragmentApplyNeedsBinding mViewBinding;


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
        return R.layout.fragment_apply_needs;
    }

    @Override
    public ApplyNeedsViewModel getViewModel() {
        mViewModel = (ApplyNeedsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ApplyNeedsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.apply_need);
        mViewModel.setUp();
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
                    mViewModel.addImage(result.getUri().toString());

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }
    }

    @Override
    public int getDegree() {
        return getArguments().getInt(AppConstants.BundleData.DEGREE, 1);
    }
}
