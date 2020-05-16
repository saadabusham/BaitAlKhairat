package com.saad.baitalkhairat.ui.intro.casedetails;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCaseDetailsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class CaseDetailsFragment extends BaseFragment<FragmentCaseDetailsBinding, CaseDetailsViewModel> implements CaseDetailsNavigator {

    private static final String TAG = CaseDetailsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private CaseDetailsViewModel mViewModel;
    private FragmentCaseDetailsBinding mViewBinding;


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
        return R.layout.fragment_case_details;
    }

    @Override
    public CaseDetailsViewModel getViewModel() {
        mViewModel = (CaseDetailsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(CaseDetailsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, "details");
        mViewModel.setUp();
    }

}
