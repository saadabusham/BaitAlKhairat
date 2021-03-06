package com.saad.baitalkhairat.ui.donatejourney.donationappliedsuccessful;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentDonorAppliedSuccessfulBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class DonorAppliedSuccessfulFragment extends BaseFragment<FragmentDonorAppliedSuccessfulBinding, DonorAppliedSuccessfulViewModel> implements DonorAppliedSuccessfulNavigator {

    private static final String TAG = DonorAppliedSuccessfulFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private DonorAppliedSuccessfulViewModel mViewModel;
    private FragmentDonorAppliedSuccessfulBinding mViewBinding;

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
        return R.layout.fragment_donor_applied_successful;
    }

    @Override
    public DonorAppliedSuccessfulViewModel getViewModel() {
        mViewModel = (DonorAppliedSuccessfulViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(DonorAppliedSuccessfulViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mViewModel.setUp();
    }

}
