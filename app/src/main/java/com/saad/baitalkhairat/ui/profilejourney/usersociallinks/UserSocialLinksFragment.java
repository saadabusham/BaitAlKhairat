package com.saad.baitalkhairat.ui.profilejourney.usersociallinks;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentUserSocialLinksBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.account.UserResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class UserSocialLinksFragment extends BaseFragment<FragmentUserSocialLinksBinding, UserSocialLinksViewModel>
        implements UserSocialLinksNavigator {

    private static final String TAG = UserSocialLinksFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private UserSocialLinksViewModel mRegisterViewModel;
    private FragmentUserSocialLinksBinding mViewBinding;


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
        return R.layout.fragment_user_social_links;
    }

    @Override
    public UserSocialLinksViewModel getViewModel() {
        mRegisterViewModel = (UserSocialLinksViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(UserSocialLinksViewModel.class, getViewDataBinding(), this);
        return mRegisterViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.social_links);
        mRegisterViewModel.setUp();
    }

    @Override
    public UserResponse getUser() {
        return (UserResponse) getArguments().getSerializable(AppConstants.BundleData.USER);
    }
}