package com.saad.baitalkhairat.ui.intro.filtercases;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentFilterCasesBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class FilterCasesFragment extends BaseFragment<FragmentFilterCasesBinding, FilterCasesViewModel> implements FilterCasesNavigator {

    private static final String TAG = FilterCasesFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private FilterCasesViewModel mViewModel;
    private FragmentFilterCasesBinding mViewBinding;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.returnData();
    }

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
        return R.layout.fragment_filter_cases;
    }

    @Override
    public FilterCasesViewModel getViewModel() {
        mViewModel = (FilterCasesViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(FilterCasesViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.filter_search);
        mViewModel.setUp();
    }

    @Override
    public boolean filterWithCategory() {
        return getArguments().getBoolean(AppConstants.BundleData.FILTER_WITH_CATEGORY, false);
    }
}
