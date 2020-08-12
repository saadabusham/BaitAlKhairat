package com.saad.baitalkhairat.ui.intro.cases;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCasesBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.Filter;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


public class CasesFragment extends BaseFragment<FragmentCasesBinding, CasesViewModel>
        implements CasesNavigator, ActivityResultCallBack {

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
        return true;
    }

    @Override
    public boolean hasOptionMenu() {
        return true;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return this::callBack;
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
        setUpToolbar(mViewBinding.toolbar, TAG, getCategoryName());

        mViewBinding.toolbar.toolbar.inflateMenu(R.menu.main_menu);
        mViewBinding.toolbar.toolbar.findViewById(R.id.itemCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().openCart();
            }
        });
        mViewBinding.toolbar.toolbar.findViewById(R.id.itemSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mHomeViewModel.setUp();
    }

    @Override
    public void callBack(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mHomeViewModel.updateFilter((Filter) data.getSerializableExtra(AppConstants.BundleData.FILTER));
        }
    }

    @Override
    public int getCategoryId() {
        return getArguments().getInt(AppConstants.BundleData.CATEGORY_ID, 0);
    }

    public String getCategoryName() {
        return getArguments().getString(AppConstants.BundleData.CATEGORY_NAME, "");
    }

}
