package com.saad.baitalkhairat.ui.needjourney.myneedslist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyNeedsListBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.MyNeeds;
import com.saad.baitalkhairat.model.Notification;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.MyNeedsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;


public class MyNeedsListViewModel extends BaseViewModel<MyNeedsListNavigator, FragmentMyNeedsListBinding>
        implements RecyclerClick<Notification> {

    MyNeedsAdapter myNeedsAdapter;
    boolean isRefreshing = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    public <V extends ViewDataBinding, N extends BaseNavigator> MyNeedsListViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MyNeedsListNavigator) navigation, (FragmentMyNeedsListBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        getData();
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
        myNeedsAdapter = new MyNeedsAdapter(getMyContext(), getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(myNeedsAdapter);
        myNeedsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                myNeedsAdapter.addItem(null);
                myNeedsAdapter.notifyItemInserted(myNeedsAdapter.getItemCount() - 1);
                getViewBinding().recyclerView.scrollToPosition(myNeedsAdapter.getItemCount() - 1);
                setLoadMore(true);
                getData();
            }
        });
        getLocalData();
    }

    private void getLocalData() {
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(2));
        myNeedsAdapter.addItem(new MyNeeds(3));
        myNeedsAdapter.addItem(new MyNeeds(4));
        myNeedsAdapter.addItem(new MyNeeds(4));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(3));
        myNeedsAdapter.addItem(new MyNeeds(2));
        myNeedsAdapter.addItem(new MyNeeds(4));
        myNeedsAdapter.addItem(new MyNeeds(4));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(1));
        myNeedsAdapter.addItem(new MyNeeds(1));
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
        getViewBinding().layoutNoDataFound.relativeListEmpty.setVisibility(View.VISIBLE);

    }

    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                myNeedsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Notification notification, int position) {
        Bundle data = new Bundle();
        data.putSerializable(AppConstants.BundleData.NOTIFICATIONS, notification);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_nav_notifications_to_notificationDetailsFragment);

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


    private void checkIsLoadMoreAndRefreshing(boolean isSuccess) {
        if (isRefreshing()) {
            finishRefreshing(isSuccess);
        } else {
            enableLoading = false;
        }
    }


    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            myNeedsAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
