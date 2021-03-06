package com.saad.baitalkhairat.ui.needjourney.myneedslist;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyNeedsListBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class MyNeedsListFragment extends BaseFragment<FragmentMyNeedsListBinding, MyNeedsListViewModel> implements MyNeedsListNavigator {

    private static final String TAG = MyNeedsListFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private MyNeedsListViewModel mViewModel;
    private FragmentMyNeedsListBinding mViewBinding;


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
        return R.layout.fragment_my_needs_list;
    }

    @Override
    public MyNeedsListViewModel getViewModel() {
        mViewModel = (MyNeedsListViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(MyNeedsListViewModel.class, getViewDataBinding(), this);
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


    @Override
    public int getNeedType() {
        return getArguments().getInt(AppConstants.BundleData.MY_NEEDS_TAB_TYPE, 0);
    }
}
