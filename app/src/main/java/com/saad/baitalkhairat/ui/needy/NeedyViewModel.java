package com.saad.baitalkhairat.ui.needy;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saad.baitalkhairat.databinding.FragmentNeedyBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Needy;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.NeedyAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;


public class NeedyViewModel extends BaseViewModel<NeedyNavigator, FragmentNeedyBinding>
        implements RecyclerClick<Needy> {

    NeedyAdapter needyAdapter;
    boolean isRefreshing = false;
    boolean isRetry = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    public <V extends ViewDataBinding, N extends BaseNavigator> NeedyViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (NeedyNavigator) navigation, (FragmentNeedyBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        getData();
        getViewBinding().layoutNoDataFound.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRetring();
                getData();
            }
        });
    }

    private void setUpRecycler() {
        getViewBinding().swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsRefreshing(true);
                getData();
            }
        });

        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        needyAdapter = new NeedyAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(needyAdapter);
        needyAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                needyAdapter.addItem(null);
                needyAdapter.notifyItemInserted(needyAdapter.getItemCount() - 1);
                getViewBinding().recyclerView.scrollToPosition(needyAdapter.getItemCount() - 1);
                setLoadMore(true);
                getData();
            }
        });
        getLocalData();
    }

    private void getLocalData() {
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
        needyAdapter.addItem(new Needy());
    }

    public void getData() {
//        if (!isRefreshing() && !isRetry()) {
//            enableLoading = true;
//        }
//        getDataManager().getHomeService().getDataApi().getHomeCategories()
//                .toObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new CustomObserverResponse<Home>(getMyContext(), enableLoading, new APICallBack<Home>() {
//                    @Override
//                    public void onSuccess(Home response) {
//                        checkIsLoadMoreAndRefreshing(true);
////                        homeAdapter.addItems(response.getCategoryList());
////                        notifiAdapter();
//                        if (response.getSliderList().size() > 0) {
//                            setUpViewPager(response.getSliderList());
//                        }
//                    }
//
//                    @Override
//                    public void onError(String error, int errorCode) {
//                        if (homeAdapter.getItemCount() == 0) {
//                            showNoDataFound();
//                        }
//                        showSnackBar(getMyContext().getString(R.string.error),
//                                error, getMyContext().getResources().getString(R.string.ok),
//                                new SnackViewBulider.SnackbarCallback() {
//                                    @Override
//                                    public void onActionClick(Snackbar snackbar) {
//                                        snackbar.dismiss();
//                                    }
//                                });
//                        checkIsLoadMoreAndRefreshing(false);
//                    }
//                }));
    }

    private void showNoDataFound() {
        getViewBinding().swipeRefreshLayout.setEnabled(false);
        getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.VISIBLE);

    }

    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                needyAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Needy needy, int position) {
//        Bundle data = new Bundle();
//        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
//                .navigate(R.id.action_nav_category_to_subCategoryFragment);

    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setIsRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    public boolean isRetry() {
        return isRetry;
    }

    public void setRetry(boolean retry) {
        isRetry = retry;
    }

    private void checkIsLoadMoreAndRefreshing(boolean isSuccess) {
        if (isRefreshing()) {
            finishRefreshing(isSuccess);
        } else if (isRetry()) {
            finishRetry(isSuccess);
        } else {
            enableLoading = false;
        }
    }

    protected void setRetring() {
        getViewBinding().layoutNoDataFound.btnRetry.setVisibility(View.GONE);
        getViewBinding().layoutNoDataFound.progressBar.setVisibility(View.VISIBLE);
        setRetry(true);
    }

    protected void finishRetry(boolean isSuccess) {

        getViewBinding().layoutNoDataFound.progressBar.setVisibility(View.GONE);
        getViewBinding().layoutNoDataFound.btnRetry.setVisibility(View.VISIBLE);
        setRetry(false);
        if (isSuccess) {
            needyAdapter.clearItems();
            getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.GONE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            needyAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
