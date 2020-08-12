package com.saad.baitalkhairat.ui.menu.about_us;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentAboutUsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class AboutUsFragment extends BaseFragment<FragmentAboutUsBinding, AboutUsViewModel> implements AboutUsNavigator {

    private static final String TAG = AboutUsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private AboutUsViewModel mViewModel;
    private FragmentAboutUsBinding mViewBinding;


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
        return R.layout.fragment_about_us;
    }

    @Override
    public AboutUsViewModel getViewModel() {
        mViewModel = (AboutUsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(AboutUsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.about_us);
        mViewModel.setUp();
    }

}
