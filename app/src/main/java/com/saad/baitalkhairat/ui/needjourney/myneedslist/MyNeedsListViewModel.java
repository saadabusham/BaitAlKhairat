package com.saad.baitalkhairat.ui.needjourney.myneedslist;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyNeedsListBinding;
import com.saad.baitalkhairat.enums.MyNeedsTabTypes;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.needs.MyNeeds;
import com.saad.baitalkhairat.model.needs.NeedResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.MyNeedsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.SnackViewBulider;


public class MyNeedsListViewModel extends BaseViewModel<MyNeedsListNavigator, FragmentMyNeedsListBinding>
        implements RecyclerClick<MyNeeds> {

    MyNeedsAdapter myNeedsAdapter;
    boolean isRefreshing = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;
    boolean isRetry = false;

    NeedResponse needResponse;

    public <V extends ViewDataBinding, N extends BaseNavigator> MyNeedsListViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MyNeedsListNavigator) navigation, (FragmentMyNeedsListBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        MyNeedsTabTypes myNeedsTabTypes = MyNeedsTabTypes.fromInt(getNavigator().getNeedType());
        switch (myNeedsTabTypes) {
            case CURRENT:
                getCurrentNeeds(1);
                break;
            case HISTORY:
                getHistoryNeeds(1);
                break;
        }

    }


    private void setUpRecycler() {
        getViewBinding().swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsRefreshing(true);
                MyNeedsTabTypes myNeedsTabTypes = MyNeedsTabTypes.fromInt(getNavigator().getNeedType());
                switch (myNeedsTabTypes) {
                    case CURRENT:
                        getCurrentNeeds(1);
                        break;
                    case HISTORY:
                        getHistoryNeeds(1);
                        break;
                }
            }
        });

        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        myNeedsAdapter = new MyNeedsAdapter(getMyContext(), getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(myNeedsAdapter);
        myNeedsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (needResponse != null &&
                        needResponse.getMeta().getCurrentPage() < needResponse.getMeta().getLastPage()) {
                    myNeedsAdapter.addItem(null);
                    myNeedsAdapter.notifyItemInserted(myNeedsAdapter.getItemCount() - 1);
                    getViewBinding().recyclerView.scrollToPosition(myNeedsAdapter.getItemCount() - 1);
                    setLoadMore(true);
                    MyNeedsTabTypes myNeedsTabTypes = MyNeedsTabTypes.fromInt(getNavigator().getNeedType());
                    switch (myNeedsTabTypes) {
                        case CURRENT:
                            getCurrentNeeds(needResponse.getMeta().getCurrentPage() + 1);
                            break;
                        case HISTORY:
                            getHistoryNeeds(needResponse.getMeta().getCurrentPage() + 1);
                            break;
                    }
                }
            }
        });
    }


    public void getCurrentNeeds(int page) {
        if (!isLoadMore() && !isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getNeedsService().getCurrentNeeds(getMyContext(), true, page, new APICallBack<NeedResponse>() {
            @Override
            public void onSuccess(NeedResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    needResponse = response;
                    myNeedsAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (myNeedsAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                if (!isLoadMore && myNeedsAdapter.getItemCount() == 0 && errorCode != 0) {
                    showSnackBar(getMyContext().getString(R.string.error),
                            error, getMyContext().getResources().getString(R.string.ok),
                            new SnackViewBulider.SnackbarCallback() {
                                @Override
                                public void onActionClick(Snackbar snackbar) {
                                    snackbar.dismiss();
                                }
                            });
                }
                checkIsLoadMoreAndRefreshing(false);
            }
        });
    }

    public void getHistoryNeeds(int page) {
        if (!isLoadMore() && !isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getNeedsService().getHistoryNeeds(getMyContext(), true, page, new APICallBack<NeedResponse>() {
            @Override
            public void onSuccess(NeedResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    needResponse = response;
                    myNeedsAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (myNeedsAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                if (!isLoadMore && myNeedsAdapter.getItemCount() == 0 && errorCode != 0) {
                    showSnackBar(getMyContext().getString(R.string.error),
                            error, getMyContext().getResources().getString(R.string.ok),
                            new SnackViewBulider.SnackbarCallback() {
                                @Override
                                public void onActionClick(Snackbar snackbar) {
                                    snackbar.dismiss();
                                }
                            });
                }
                checkIsLoadMoreAndRefreshing(false);
            }
        });
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
    public void onClick(MyNeeds myNeeds, int position) {


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
        } else if (isLoadMore()) {
            finishLoadMore();
        } else {
            enableLoading = false;
        }
    }


    public void finishLoadMore() {
        myNeedsAdapter.remove(myNeedsAdapter.getItemCount() - 1);
        myNeedsAdapter.notifyItemRemoved(myNeedsAdapter.getItemCount());
        myNeedsAdapter.setLoaded();
        setLoadMore(false);
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            myNeedsAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
