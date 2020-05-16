package com.saad.baitalkhairat.ui.menu.servicecenter;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentServiceCenterBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ServiceCenterFragment extends BaseFragment<FragmentServiceCenterBinding, ServiceCenterViewModel> implements ServiceCenterNavigator {

    private static final String TAG = ServiceCenterFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ServiceCenterViewModel mViewModel;
    private FragmentServiceCenterBinding mViewBinding;


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
        return R.layout.fragment_service_center;
    }

    @Override
    public ServiceCenterViewModel getViewModel() {
        mViewModel = (ServiceCenterViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ServiceCenterViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.service_center);
        mViewModel.setUp();
    }

}
