package com.saad.baitalkhairat.ui.profilejourney.myinfolist;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyInfoListBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.account.UserResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class MyInfoListFragment extends BaseFragment<FragmentMyInfoListBinding, MyInfoListViewModel> implements MyInfoListNavigator {

    private static final String TAG = MyInfoListFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private MyInfoListViewModel mViewModel;
    private FragmentMyInfoListBinding mViewBinding;


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
        return R.layout.fragment_my_info_list;
    }

    @Override
    public MyInfoListViewModel getViewModel() {
        mViewModel = (MyInfoListViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(MyInfoListViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.edit_profile);
        mViewModel.setUp();
    }


    @Override
    public UserResponse getUser() {
        return (UserResponse) getArguments().getSerializable(AppConstants.BundleData.USER);
    }
}
