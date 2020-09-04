package com.saad.baitalkhairat.ui.menu.termsofuse;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentTermsOfUseBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.app.termsofuse.TermsOfUse;
import com.saad.baitalkhairat.model.app.termsofuse.TermsOfUseResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.TermsOfUseAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.SnackViewBulider;


public class TermsOfUseViewModel extends BaseViewModel<TermsOfUseNavigator, FragmentTermsOfUseBinding>
        implements RecyclerClick<TermsOfUse> {

    TermsOfUseAdapter termsOfUseAdapter;
    boolean isRefreshing = false;
    boolean isRetry = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    TermsOfUseResponse termsOfUseResponse;

    public <V extends ViewDataBinding, N extends BaseNavigator> TermsOfUseViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (TermsOfUseNavigator) navigation, (FragmentTermsOfUseBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        getData(1);
        getViewBinding().layoutNoDataFound.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRetring();
                getData(1);
            }
        });
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
        termsOfUseAdapter = new TermsOfUseAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(termsOfUseAdapter);
        termsOfUseAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (termsOfUseResponse != null &&
                        termsOfUseResponse.getMeta().getCurrentPage() < termsOfUseResponse.getMeta().getLastPage()) {
                    termsOfUseAdapter.addItem(null);
                    termsOfUseAdapter.notifyItemInserted(termsOfUseAdapter.getItemCount() - 1);
                    getViewBinding().recyclerView.scrollToPosition(termsOfUseAdapter.getItemCount() - 1);
                    setLoadMore(true);
                    getData(termsOfUseResponse.getMeta().getCurrentPage() + 1);
                }
            }
        });
    }

    public void getData(int page) {
        if (!isLoadMore() && !isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getAppService().getTermsOfUse(getMyContext(), true, page, new APICallBack<TermsOfUseResponse>() {
            @Override
            public void onSuccess(TermsOfUseResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    termsOfUseResponse = response;
                    termsOfUseAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (termsOfUseAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                if (!isLoadMore && termsOfUseAdapter.getItemCount() == 0 && errorCode != 0) {
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
                termsOfUseAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(TermsOfUse termsOfUse, int position) {
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
        } else if (isRetry()) {
            finishRetry(isSuccess);
        } else if (isLoadMore()) {
            finishLoadMore();
        } else {
            enableLoading = false;
        }
    }

    public void finishLoadMore() {
        termsOfUseAdapter.remove(termsOfUseAdapter.getItemCount() - 1);
        termsOfUseAdapter.notifyItemRemoved(termsOfUseAdapter.getItemCount());
        termsOfUseAdapter.setLoaded();
        setLoadMore(false);
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
            termsOfUseAdapter.clearItems();
            getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.GONE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            termsOfUseAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
