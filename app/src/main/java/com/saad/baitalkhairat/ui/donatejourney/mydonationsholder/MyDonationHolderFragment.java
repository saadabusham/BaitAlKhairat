package com.saad.baitalkhairat.ui.donatejourney.mydonationsholder;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyDonationHolderBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class MyDonationHolderFragment extends BaseFragment<FragmentMyDonationHolderBinding, MyDonationHolderViewModel>
        implements MyDonationHolderNavigator {

    private static final String TAG = MyDonationHolderFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private MyDonationHolderViewModel mViewModel;
    private FragmentMyDonationHolderBinding mViewBinding;


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
    public boolean hasOptionMenu() {
        return false;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_donation_holder;
    }

    @Override
    public MyDonationHolderViewModel getViewModel() {
        mViewModel = (MyDonationHolderViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(MyDonationHolderViewModel.class, getViewDataBinding(), this);
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
    public FragmentManager getChildFragment() {
        return getChildFragmentManager();
    }
}
