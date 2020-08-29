package com.saad.baitalkhairat.ui.donatejourney.mydonationslist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyDonationListBinding;
import com.saad.baitalkhairat.enums.MyNeedsTabTypes;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.donors.DonorResponse;
import com.saad.baitalkhairat.model.donors.MyDonors;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.MyDonorsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.SnackViewBulider;


public class MyDonationListViewModel extends BaseViewModel<MyDonationListNavigator, FragmentMyDonationListBinding>
        implements RecyclerClick<MyDonors> {

    MyDonorsAdapter myDonorsAdapter;
    boolean isRefreshing = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;
    boolean isRetry = false;

    DonorResponse donorResponse;
    public <V extends ViewDataBinding, N extends BaseNavigator> MyDonationListViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MyDonationListNavigator) navigation, (FragmentMyDonationListBinding) viewDataBinding);
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
        myDonorsAdapter = new MyDonorsAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(myDonorsAdapter);
        myDonorsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (donorResponse != null &&
                        donorResponse.getMeta().getCurrentPage() < donorResponse.getMeta().getLastPage()) {
                    myDonorsAdapter.addItem(null);
                    myDonorsAdapter.notifyItemInserted(myDonorsAdapter.getItemCount() - 1);
                    getViewBinding().recyclerView.scrollToPosition(myDonorsAdapter.getItemCount() - 1);
                    setLoadMore(true);
                    MyNeedsTabTypes myNeedsTabTypes = MyNeedsTabTypes.fromInt(getNavigator().getNeedType());
                    switch (myNeedsTabTypes) {
                        case CURRENT:
                            getCurrentNeeds(donorResponse.getMeta().getCurrentPage() + 1);
                            break;
                        case HISTORY:
                            getHistoryNeeds(donorResponse.getMeta().getCurrentPage() + 1);
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
        getDataManager().getDonorsService().getCurrentNeeds(getMyContext(), true, page, new APICallBack<DonorResponse>() {
            @Override
            public void onSuccess(DonorResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    donorResponse = response;
                    myDonorsAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (myDonorsAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                showSnackBar(getMyContext().getString(R.string.error),
                        error, getMyContext().getResources().getString(R.string.ok),
                        new SnackViewBulider.SnackbarCallback() {
                            @Override
                            public void onActionClick(Snackbar snackbar) {
                                snackbar.dismiss();
                            }
                        });
                checkIsLoadMoreAndRefreshing(false);
            }
        });
    }

    public void getHistoryNeeds(int page) {
        if (!isLoadMore() && !isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getDonorsService().getHistoryNeeds(getMyContext(), true, page, new APICallBack<DonorResponse>() {
            @Override
            public void onSuccess(DonorResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    donorResponse = response;
                    myDonorsAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (myDonorsAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                showSnackBar(getMyContext().getString(R.string.error),
                        error, getMyContext().getResources().getString(R.string.ok),
                        new SnackViewBulider.SnackbarCallback() {
                            @Override
                            public void onActionClick(Snackbar snackbar) {
                                snackbar.dismiss();
                            }
                        });
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
                myDonorsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(MyDonors myDonors, int position) {
        Bundle data = new Bundle();
        data.putSerializable(AppConstants.BundleData.DONORS, myDonors);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.donorsDetailsFragment, data);

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
        myDonorsAdapter.remove(myDonorsAdapter.getItemCount() - 1);
        myDonorsAdapter.notifyItemRemoved(myDonorsAdapter.getItemCount());
        myDonorsAdapter.setLoaded();
        setLoadMore(false);
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            myDonorsAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
