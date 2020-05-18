package com.saad.baitalkhairat.ui.needjourney.needappliedsuccessful;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNeedAppliedSuccessfulBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class NeedAppliedSuccessfulFragment extends BaseFragment<FragmentNeedAppliedSuccessfulBinding, NeedAppliedSuccessfulViewModel> implements NeedAppliedSuccessfulNavigator {

    private static final String TAG = NeedAppliedSuccessfulFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private NeedAppliedSuccessfulViewModel mViewModel;
    private FragmentNeedAppliedSuccessfulBinding mViewBinding;

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
        return R.layout.fragment_need_applied_successful;
    }

    @Override
    public NeedAppliedSuccessfulViewModel getViewModel() {
        mViewModel = (NeedAppliedSuccessfulViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(NeedAppliedSuccessfulViewModel.class, getViewDataBinding(), this);
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
