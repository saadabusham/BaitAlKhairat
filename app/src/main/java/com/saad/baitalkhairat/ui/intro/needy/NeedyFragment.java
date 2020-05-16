package com.saad.baitalkhairat.ui.intro.needy;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNeedyBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class NeedyFragment extends BaseFragment<FragmentNeedyBinding, NeedyViewModel> implements NeedyNavigator {

    private static final String TAG = NeedyFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private NeedyViewModel mViewModel;
    private FragmentNeedyBinding mViewBinding;


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
        return R.layout.fragment_needy;
    }

    @Override
    public NeedyViewModel getViewModel() {
        mViewModel = (NeedyViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(NeedyViewModel.class, getViewDataBinding(), this);
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
