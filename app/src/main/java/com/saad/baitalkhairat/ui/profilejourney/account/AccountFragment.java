package com.saad.baitalkhairat.ui.profilejourney.account;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentAccountBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class AccountFragment extends BaseFragment<FragmentAccountBinding, AccountViewModel> implements AccountNavigator {

    private static final String TAG = AccountFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private AccountViewModel mViewModel;
    private FragmentAccountBinding mViewBinding;

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
        return R.layout.fragment_account;
    }

    @Override
    public AccountViewModel getViewModel() {
        mViewModel = (AccountViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(AccountViewModel.class, getViewDataBinding(), this);
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
