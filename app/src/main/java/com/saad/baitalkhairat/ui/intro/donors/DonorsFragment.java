package com.saad.baitalkhairat.ui.intro.donors;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentDonorsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class DonorsFragment extends BaseFragment<FragmentDonorsBinding, DonorsViewModel> implements DonorsNavigator {

    private static final String TAG = DonorsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private DonorsViewModel mHomeViewModel;
    private FragmentDonorsBinding mViewBinding;


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
        return false;
    }

    @Override
    public boolean hideBottomSheet() {
        return false;
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
        return R.layout.fragment_donors;
    }

    @Override
    public DonorsViewModel getViewModel() {
        mHomeViewModel = (DonorsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(DonorsViewModel.class, getViewDataBinding(), this);
        return mHomeViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mHomeViewModel.setUp();
    }


}
