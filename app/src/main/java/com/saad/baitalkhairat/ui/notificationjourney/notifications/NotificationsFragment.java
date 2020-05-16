package com.saad.baitalkhairat.ui.notificationjourney.notifications;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNotificationsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class NotificationsFragment extends BaseFragment<FragmentNotificationsBinding, NotificationsViewModel> implements NotificationsNavigator {

    private static final String TAG = NotificationsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private NotificationsViewModel mViewModel;
    private FragmentNotificationsBinding mViewBinding;


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
        return R.layout.fragment_notifications;
    }

    @Override
    public NotificationsViewModel getViewModel() {
        mViewModel = (NotificationsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(NotificationsViewModel.class, getViewDataBinding(), this);
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
