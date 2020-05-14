package com.saad.baitalkhairat.ui.changelanguage;

import android.content.Context;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChangeLanguageBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class ChangeLanguageFragment extends BaseFragment<FragmentChangeLanguageBinding, ChangeLanguageViewModel> implements ChangeLanguageNavigator {

    private static final String TAG = ChangeLanguageFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ChangeLanguageViewModel mChangeLanguageViewModel;
    private FragmentChangeLanguageBinding mViewBinding;

    public static ChangeLanguageFragment newInstance() {
        Bundle args = new Bundle();
        ChangeLanguageFragment fragment = new ChangeLanguageFragment();
        fragment.setArguments(args);
        return fragment;
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
        return R.layout.fragment_change_language;
    }

    @Override
    public ChangeLanguageViewModel getViewModel() {
        mChangeLanguageViewModel = (ChangeLanguageViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(ChangeLanguageViewModel.class, getViewDataBinding(), this);
        return mChangeLanguageViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mChangeLanguageViewModel.setUp();
        setUpToolbar(mViewBinding.toolbar, TAG, getMyContext().getString(R.string.change_language));
    }


}
