package com.saad.baitalkhairat.ui.auth.signinholder;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentSigninHolderBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class SignInHolderFragment extends BaseFragment<FragmentSigninHolderBinding, SignInHolderViewModel>
        implements SignInHolderNavigator {

    private static final String TAG = SignInHolderFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SignInHolderViewModel mViewModel;
    private FragmentSigninHolderBinding mViewBinding;


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
        return R.layout.fragment_signin_holder;
    }

    @Override
    public SignInHolderViewModel getViewModel() {
        mViewModel = (SignInHolderViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(SignInHolderViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.login_or_register);
        mViewModel.setUp();
    }

    @Override
    public int getSignType() {
        return getArguments() == null ? 0 :
                getArguments().getInt("type", 0);
    }

    @Override
    public FragmentManager getChildFragment() {
        return getChildFragmentManager();
    }
}
