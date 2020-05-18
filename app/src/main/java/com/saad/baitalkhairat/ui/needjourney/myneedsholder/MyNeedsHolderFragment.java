package com.saad.baitalkhairat.ui.needjourney.myneedsholder;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyNeedsHolderBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class MyNeedsHolderFragment extends BaseFragment<FragmentMyNeedsHolderBinding, MyNeedsHolderViewModel>
        implements MyNeedsHolderNavigator {

    private static final String TAG = MyNeedsHolderFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private MyNeedsHolderViewModel mViewModel;
    private FragmentMyNeedsHolderBinding mViewBinding;


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
        return R.layout.fragment_my_needs_holder;
    }

    @Override
    public MyNeedsHolderViewModel getViewModel() {
        mViewModel = (MyNeedsHolderViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(MyNeedsHolderViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.my_needs);
        mViewModel.setUp();
    }

    @Override
    public FragmentManager getChildFragment() {
        return getChildFragmentManager();
    }
}
