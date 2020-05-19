package com.saad.baitalkhairat.ui.walletjourney.chargedsuccessful;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChargedSuccessfulBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ChargedSuccessfulFragment extends BaseFragment<FragmentChargedSuccessfulBinding, ChargedSuccessfulViewModel>
        implements ChargedSuccessfulNavigator {

    private static final String TAG = ChargedSuccessfulFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ChargedSuccessfulViewModel mViewModel;
    private FragmentChargedSuccessfulBinding mViewBinding;


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
        return R.layout.fragment_charged_successful;
    }

    @Override
    public ChargedSuccessfulViewModel getViewModel() {
        mViewModel = (ChargedSuccessfulViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ChargedSuccessfulViewModel.class, getViewDataBinding(), this);
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
