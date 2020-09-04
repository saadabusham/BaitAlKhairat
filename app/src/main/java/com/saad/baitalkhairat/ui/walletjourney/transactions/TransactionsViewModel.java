package com.saad.baitalkhairat.ui.walletjourney.transactions;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentTransactionsBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.wallet.Transaction;
import com.saad.baitalkhairat.model.wallet.TransactionResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.TransactionAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.SnackViewBulider;


public class TransactionsViewModel extends BaseViewModel<TransactionsNavigator, FragmentTransactionsBinding>
        implements RecyclerClick<Transaction> {

    TransactionAdapter transactionAdapter;
    boolean isRefreshing = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;
    boolean isRetry = false;
    TransactionResponse transactionResponse;

    public <V extends ViewDataBinding, N extends BaseNavigator> TransactionsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (TransactionsNavigator) navigation, (FragmentTransactionsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        getData(1);
    }

    private void setUpRecycler() {
        getViewBinding().swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsRefreshing(true);
                getData(1);
            }
        });

        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        transactionAdapter = new TransactionAdapter(getMyContext(), getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(transactionAdapter);
        transactionAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (transactionResponse != null &&
                        transactionResponse.getMeta().getCurrentPage() < transactionResponse.getMeta().getLastPage()) {
                    transactionAdapter.addItem(null);
                    transactionAdapter.notifyItemInserted(transactionAdapter.getItemCount() - 1);
                    getViewBinding().recyclerView.scrollToPosition(transactionAdapter.getItemCount() - 1);
                    setLoadMore(true);
                    getData(transactionResponse.getMeta().getCurrentPage() + 1);
                }
            }
        });
    }


    public void getData(int page) {
        if (!isLoadMore() && !isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getWalletService().getTransactions(getMyContext(), true, page, new APICallBack<TransactionResponse>() {
            @Override
            public void onSuccess(TransactionResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    transactionResponse = response;
                    transactionAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (transactionAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                if (!isLoadMore && transactionAdapter.getItemCount() == 0 && errorCode != 0) {
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
        getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.VISIBLE);

    }

    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                transactionAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Transaction transaction, int position) {
//        Bundle data = new Bundle();
//        data.putSerializable(AppConstants.BundleData.NOTIFICATIONS, notification);
//        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
//                .navigate(R.id.action_nav_notifications_to_notificationDetailsFragment);

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
        transactionAdapter.remove(transactionAdapter.getItemCount() - 1);
        transactionAdapter.notifyItemRemoved(transactionAdapter.getItemCount());
        transactionAdapter.setLoaded();
        setLoadMore(false);
    }


    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            transactionAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
