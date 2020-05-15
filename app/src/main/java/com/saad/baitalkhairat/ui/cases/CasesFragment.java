package com.saad.baitalkhairat.ui.cases;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCasesBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class CasesFragment extends BaseFragment<FragmentCasesBinding, CasesViewModel>
        implements CasesNavigator {

    private static final String TAG = CasesFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private CasesViewModel mHomeViewModel;
    private FragmentCasesBinding mViewBinding;


    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public boolean hideToolbar() {
        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
    public boolean hasOptionMenu() {
        return true;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cases;
    }

    @Override
    public CasesViewModel getViewModel() {
        mHomeViewModel = (CasesViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(CasesViewModel.class, getViewDataBinding(), this);
        return mHomeViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.privacy_policy);

        mViewBinding.toolbar.toolbar.inflateMenu(R.menu.main_menu);
        mViewBinding.toolbar.toolbar.findViewById(R.id.itemCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mViewBinding.toolbar.toolbar.findViewById(R.id.itemSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mHomeViewModel.setUp();
    }

}
