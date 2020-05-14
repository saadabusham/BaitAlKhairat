package com.saad.baitalkhairat.ui.emptyactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityEmptyBinding;
import com.saad.baitalkhairat.model.DataExample;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

public class EmptyActivity extends BaseActivity<ActivityEmptyBinding, EmptyActivityViewModel>
        implements EmptyActivityNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private EmptyActivityViewModel mEmptyViewModel;
    private ActivityEmptyBinding mViewBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, EmptyActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public void setUpToolbar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    public EmptyActivityViewModel getViewModel() {
        mEmptyViewModel = (EmptyActivityViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(EmptyActivityViewModel.class, getViewDataBinding(), this);
        return mEmptyViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();

        mEmptyViewModel.getLoadingStatus().observe(this, new LoadingObserver());
        mEmptyViewModel.getData().observe(this, new DataObserver());
//        mEmptyViewModel.loadData();
    }


    //Observers
    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if (isLoading == null) return;

            if (isLoading) {
//                progressBar.setVisibility(View.VISIBLE);
            } else {
//                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private class DataObserver implements Observer<List<DataExample>> {
        @Override
        public void onChanged(@Nullable List<DataExample> dataExampleList) {
            if (dataExampleList == null) return;
//            dataAdapter.setItems(movies);
//
//            if (dataExampleList.isEmpty()) {
//                emptyView.setVisibility(View.VISIBLE);
//            } else {
//                emptyView.setVisibility(View.GONE);
//            }
        }
    }
}