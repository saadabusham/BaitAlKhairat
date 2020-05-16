package com.saad.baitalkhairat.ui.notificationjourney.notificationdetails;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNotificationDetailsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class NotificationDetailsFragment extends BaseFragment<FragmentNotificationDetailsBinding, NotificationDetailsViewModel>
        implements NotificationDetailsNavigator {

    private static final String TAG = NotificationDetailsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private NotificationDetailsViewModel mViewModel;
    private FragmentNotificationDetailsBinding mViewBinding;


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
        return R.layout.fragment_notification_details;
    }

    @Override
    public NotificationDetailsViewModel getViewModel() {
        mViewModel = (NotificationDetailsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(NotificationDetailsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.details);
        mViewModel.setUp();
    }

}
