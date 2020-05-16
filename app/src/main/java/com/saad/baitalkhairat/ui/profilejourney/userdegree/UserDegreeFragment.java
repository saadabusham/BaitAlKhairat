package com.saad.baitalkhairat.ui.profilejourney.userdegree;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentUserDegreeBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class UserDegreeFragment extends BaseFragment<FragmentUserDegreeBinding, UserDegreeViewModel>
        implements UserDegreeNavigator {

    private static final String TAG = UserDegreeFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private UserDegreeViewModel mRegisterViewModel;
    private FragmentUserDegreeBinding mViewBinding;


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
        return R.layout.fragment_user_degree;
    }

    @Override
    public UserDegreeViewModel getViewModel() {
        mRegisterViewModel = (UserDegreeViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(UserDegreeViewModel.class, getViewDataBinding(), this);
        return mRegisterViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.information_about_my_degree);
        mRegisterViewModel.setUp();
    }
}