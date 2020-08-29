package com.saad.baitalkhairat.ui.donatejourney.donordetails;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentDonorsDetailsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.donors.MyDonors;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class DonorsDetailsFragment extends BaseFragment<FragmentDonorsDetailsBinding, DonorsDetailsViewModel> implements DonorsDetailsNavigator {

    private static final String TAG = DonorsDetailsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private DonorsDetailsViewModel mViewModel;
    private FragmentDonorsDetailsBinding mViewBinding;


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
        return R.layout.fragment_donors_details;
    }

    @Override
    public DonorsDetailsViewModel getViewModel() {
        mViewModel = (DonorsDetailsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(DonorsDetailsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.my_donations);
        mViewModel.setUp();
    }

    @Override
    public MyDonors getDonorsObj() {
        return (MyDonors) getArguments().getSerializable(AppConstants.BundleData.DONORS);
    }
}
