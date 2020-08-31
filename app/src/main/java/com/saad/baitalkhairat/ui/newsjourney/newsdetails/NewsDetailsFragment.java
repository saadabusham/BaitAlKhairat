package com.saad.baitalkhairat.ui.newsjourney.newsdetails;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNewsDetailsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.news.News;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class NewsDetailsFragment extends BaseFragment<FragmentNewsDetailsBinding, NewsDetailsViewModel>
        implements NewsDetailsNavigator {

    private static final String TAG = NewsDetailsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private NewsDetailsViewModel mViewModel;
    private FragmentNewsDetailsBinding mViewBinding;


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
        return R.layout.fragment_news_details;
    }

    @Override
    public NewsDetailsViewModel getViewModel() {
        mViewModel = (NewsDetailsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(NewsDetailsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.details);
        mViewModel.setUp();
    }

    @Override
    public News getNews() {
        return (News) getArguments().getSerializable(AppConstants.BundleData.NEWS);
    }
}
