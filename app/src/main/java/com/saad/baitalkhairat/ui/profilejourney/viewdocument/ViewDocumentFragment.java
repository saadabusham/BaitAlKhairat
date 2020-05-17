package com.saad.baitalkhairat.ui.profilejourney.viewdocument;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentViewDocumentBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.IdentificationDocument;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ViewDocumentFragment extends BaseFragment<FragmentViewDocumentBinding, ViewDocumentViewModel> implements ViewDocumentNavigator {

    private static final String TAG = ViewDocumentFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ViewDocumentViewModel mViewModel;
    private FragmentViewDocumentBinding mViewBinding;


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
        return false;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_document;
    }

    @Override
    public ViewDocumentViewModel getViewModel() {
        mViewModel = (ViewDocumentViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ViewDocumentViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.my_identification_papers);
        mViewModel.setUp();
    }


    @Override
    public IdentificationDocument getDocument() {
        return (IdentificationDocument) getArguments().getSerializable(AppConstants.BundleData.DOCUMENT);
    }
}
