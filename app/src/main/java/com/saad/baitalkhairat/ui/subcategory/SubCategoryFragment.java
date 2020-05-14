package com.saad.baitalkhairat.ui.subcategory;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentSubCategoryBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class SubCategoryFragment extends BaseFragment<FragmentSubCategoryBinding, SubCategoryViewModel>
        implements SubCategoryNavigator {

    private static final String TAG = SubCategoryFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private SubCategoryViewModel mHomeViewModel;
    private FragmentSubCategoryBinding mViewBinding;


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
        return R.layout.fragment_sub_category;
    }

    @Override
    public SubCategoryViewModel getViewModel() {
        mHomeViewModel = (SubCategoryViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(SubCategoryViewModel.class, getViewDataBinding(), this);
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
