package com.saad.baitalkhairat.ui.cartjourney.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCartBinding;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.cart.Cart;
import com.saad.baitalkhairat.model.cart.CartResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponseNoStandard;
import com.saad.baitalkhairat.ui.adapter.CartAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.DeviceUtils;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CartViewModel extends BaseViewModel<CartNavigator, FragmentCartBinding>
        implements RecyclerClick<Cart> {

    CartAdapter cartAdapter;
    boolean isRefreshing = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    CartResponse cartResponse;

    NavOptions navOptions;
    NavOptions.Builder navBuilder = new NavOptions.Builder();

    public <V extends ViewDataBinding, N extends BaseNavigator> CartViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (CartNavigator) navigation, (FragmentCartBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        getViewBinding().layoutNoDataFound.tvStartDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putInt(AppConstants.BundleData.CATEGORY_ID, 0);
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.casesFragment, data, navBuilder.setPopUpTo(R.id.nav_graph, true).build());
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
        cartAdapter = new CartAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(cartAdapter);
        cartAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (cartResponse != null &&
                        cartResponse.getMeta().getCurrentPage() < cartResponse.getMeta().getLastPage()) {
                    cartAdapter.addItem(null);
                    cartAdapter.notifyItemInserted(cartAdapter.getItemCount() - 1);
                    getViewBinding().recyclerView.scrollToPosition(cartAdapter.getItemCount() - 1);
                    setLoadMore(true);
                    getData(cartResponse.getMeta().getCurrentPage() + 1);
                }
            }
        });
        getData(1);
    }

    public void onDonateClick() {
        makeDonation();
    }

    private void makeDonation() {
        getDataManager().getDonorsService().checkout(getMyContext(), true,
                DeviceUtils.getUDID(getMyContext()),
                new APICallBack<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.action_cartFragment_to_donorAppliedSuccessfulFragment);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showToast(error);
                    }
                });
    }

    public void getData(int page) {
        if (!isLoadMore() && !isRefreshing()) {
            enableLoading = true;
        }
        getDataManager().getDonorsService().getDataApi().getCart(page)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponseNoStandard<CartResponse>(getMyContext(), enableLoading, new APICallBack<CartResponse>() {
                    @Override
                    public void onSuccess(CartResponse response) {
                        checkIsLoadMoreAndRefreshing(true);
                        if (response.getData() != null && response.getData().size() > 0) {
                            getViewBinding().setData(response);
                            cartResponse = response;
                            cartAdapter.addItems(response.getData());
                            notifiAdapter();
                        } else {
                            onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                        }
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        if (cartAdapter.getItemCount() == 0) {
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
                }));
    }

    private void showNoDataFound() {
        getViewBinding().swipeRefreshLayout.setEnabled(false);
        getViewBinding().layoutNoDataFound.relativeListEmpty.setVisibility(View.VISIBLE);
        getViewBinding().linearTools.setVisibility(View.GONE);

    }

    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Cart cart, int position) {
        removeCart(cart, position);
    }

    private void removeCart(Cart cart, int position) {
        getDataManager().getDonorsService().deleteCart(getMyContext(), true,
                DeviceUtils.getUDID(getMyContext()), cart.getId(),
                new APICallBack<CartResponse>() {
                    @Override
                    public void onSuccess(CartResponse response) {
                        if (cartAdapter.getItemCount() - 1 == 0) {
                            showNoDataFound();
                        } else {
                            cartAdapter.remove(position);
                            getViewBinding().getData().setTotal_amount(response.getTotal_amount());
                            getViewBinding().getData().setTotal_item(response.getTotal_item());
                            getViewBinding().setData(getViewBinding().getData());
                        }
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showErrorSnackBar(error);
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

    private void checkIsLoadMoreAndRefreshing(boolean isSuccess) {
        if (isRefreshing()) {
            finishRefreshing(isSuccess);
        } else if (isLoadMore()) {
            finishLoadMore();
        } else {
            enableLoading = false;
        }
        finishRetry(isSuccess);
    }

    public void finishLoadMore() {
        cartAdapter.remove(cartAdapter.getItemCount() - 1);
        cartAdapter.notifyItemRemoved(cartAdapter.getItemCount());
        cartAdapter.setLoaded();
        setLoadMore(false);
    }

    protected void finishRetry(boolean isSuccess) {
        if (isSuccess) {
            cartAdapter.clearItems();
            getViewBinding().layoutNoDataFound.relativeListEmpty.setVisibility(View.GONE);
            getViewBinding().linearTools.setVisibility(View.VISIBLE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            cartAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
