package com.saad.baitalkhairat.ui.menu.termsofuse;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentTermsOfUseBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class TermsOfUseFragment extends BaseFragment<FragmentTermsOfUseBinding, TermsOfUseViewModel> implements TermsOfUseNavigator {

    private static final String TAG = TermsOfUseFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private TermsOfUseViewModel mViewModel;
    private FragmentTermsOfUseBinding mViewBinding;


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
        return R.layout.fragment_terms_of_use;
    }

    @Override
    public TermsOfUseViewModel getViewModel() {
        mViewModel = (TermsOfUseViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(TermsOfUseViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.terms_of_use);
        mViewModel.setUp();
    }


}
