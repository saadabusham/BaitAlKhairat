package com.saad.baitalkhairat.ui.intro.cases;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCasesBinding;
import com.saad.baitalkhairat.enums.RecycleClickCasesTypes;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClickWithCase;
import com.saad.baitalkhairat.model.Case;
import com.saad.baitalkhairat.model.donors.CasesResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.CaseGridAdapter;
import com.saad.baitalkhairat.ui.adapter.CaseListAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import java.util.ArrayList;


public class CasesViewModel extends BaseViewModel<CasesNavigator, FragmentCasesBinding>
        implements RecyclerClickWithCase<Case>, OnLoadMoreListener {

    CaseGridAdapter caseGridAdapter;
    CaseListAdapter caseListAdapter;
    boolean isRefreshing = false;
    boolean isRetry = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    ArrayList<Case> caseArrayList;

    public <V extends ViewDataBinding, N extends BaseNavigator> CasesViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (CasesNavigator) navigation, (FragmentCasesBinding) viewDataBinding);
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

    public void onGridViewClicked() {
        getViewBinding().imgGridView.setImageResource(R.drawable.ic_gridview_filled);
        getViewBinding().imgListView.setImageResource(R.drawable.ic_listview_outline);
        getViewBinding().recyclerView.setLayoutManager(new GridLayoutManager(getMyContext(), 2));
        getViewBinding().recyclerView.setAdapter(caseGridAdapter);
        caseListAdapter.setOnLoadMoreListener(null);
        caseGridAdapter.setOnLoadMoreListener(this::onLoadMore);
    }

    public void onListViewClicked() {
        getViewBinding().imgGridView.setImageResource(R.drawable.ic_gridview_outline);
        getViewBinding().imgListView.setImageResource(R.drawable.ic_listview_filled);
        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setAdapter(caseListAdapter);
        caseGridAdapter.setOnLoadMoreListener(null);
        caseListAdapter.setOnLoadMoreListener(this::onLoadMore);
    }

    public void onFilterClicked() {
        Bundle data = new Bundle();
        data.putBoolean(AppConstants.BundleData.FILTER_WITH_CATEGORY,
                getNavigator().getCategoryId() == 0);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_casesFragment_to_filterCasesFragment, data);
    }

    private void setUpRecycler() {
        caseArrayList = new ArrayList<>();
        getViewBinding().swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsRefreshing(true);
                getData();
            }
        });

        getViewBinding().recyclerView.setLayoutManager(new GridLayoutManager(getMyContext(), 2));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        caseGridAdapter = new CaseGridAdapter(getMyContext(), caseArrayList, this, getViewBinding().recyclerView);
        caseListAdapter = new CaseListAdapter(getMyContext(), caseArrayList, this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(caseGridAdapter);
        caseGridAdapter.setOnLoadMoreListener(this::onLoadMore);
    }


    public void getData() {
        if (!isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getDonorsService().getCases(getMyContext(), enableLoading, getNavigator().getCategoryId(), new APICallBack<CasesResponse>() {
            @Override
            public void onSuccess(CasesResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    caseArrayList.addAll(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (caseListAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                showSnackBar(getMyContext().getString(R.string.error),
                        error, getMyContext().getResources().getString(R.string.OK),
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
        getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.VISIBLE);

    }

    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                caseGridAdapter.notifyDataSetChanged();
                caseListAdapter.notifyDataSetChanged();
            }
        });
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
            caseArrayList.clear();
            getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.GONE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            caseArrayList.clear();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        caseArrayList.add(null);
        caseGridAdapter.notifyItemInserted(caseArrayList.size() - 1);
        caseListAdapter.notifyItemInserted(caseArrayList.size() - 1);
        getViewBinding().recyclerView.scrollToPosition(caseArrayList.size() - 1);
        setLoadMore(true);
        getData();
    }

    @Override
    public void onClick(Case aCase, int position, int caseNumber) {
        Bundle data = new Bundle();


        RecycleClickCasesTypes casesTypes = RecycleClickCasesTypes.fromInt(caseNumber);
        switch (casesTypes) {

            case DETAILS:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_casesFragment_to_caseDetailsFragment,
                                data);
                break;

            case ADD_TO_CART:

                break;

            case DONATE:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_casesFragment_to_donorAppliedSuccessfulFragment);
                break;
        }
    }
}
