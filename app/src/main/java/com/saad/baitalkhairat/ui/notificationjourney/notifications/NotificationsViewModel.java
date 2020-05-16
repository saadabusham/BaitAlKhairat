package com.saad.baitalkhairat.ui.notificationjourney.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNotificationsBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Notification;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.NotificationsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.AppConstants;


public class NotificationsViewModel extends BaseViewModel<NotificationsNavigator, FragmentNotificationsBinding>
        implements RecyclerClick<Notification> {

    NotificationsAdapter notificationsAdapter;
    boolean isRefreshing = false;
    boolean isRetry = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    public <V extends ViewDataBinding, N extends BaseNavigator> NotificationsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (NotificationsNavigator) navigation, (FragmentNotificationsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        SessionManager.setIsThereNotification(false);
        ((MainActivity) getBaseActivity()).getViewDataBinding().appBarMain.drawerMainContent.bottomSheet.getMenu().getItem(2)
                .setIcon(getMyContext().getResources().getDrawable(R.drawable.ic_notification_nav));
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
        notificationsAdapter = new NotificationsAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(notificationsAdapter);
        notificationsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                notificationsAdapter.addItem(null);
                notificationsAdapter.notifyItemInserted(notificationsAdapter.getItemCount() - 1);
                getViewBinding().recyclerView.scrollToPosition(notificationsAdapter.getItemCount() - 1);
                setLoadMore(true);
                getData();
            }
        });
        getLocalData();
    }

    private void getLocalData() {
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());
        notificationsAdapter.addItem(new Notification());

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
                notificationsAdapter.notifyDataSetChanged();
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
            notificationsAdapter.clearItems();
            getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.GONE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            notificationsAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
