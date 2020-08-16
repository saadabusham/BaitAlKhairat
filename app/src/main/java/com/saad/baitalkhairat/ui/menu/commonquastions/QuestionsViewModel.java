package com.saad.baitalkhairat.ui.menu.commonquastions;

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
import com.saad.baitalkhairat.databinding.FragmentCommonQuastionsBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.interfaces.OnLoadMoreListener;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Notification;
import com.saad.baitalkhairat.model.quastion.QuestionResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.QuestionsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.SnackViewBulider;


public class QuestionsViewModel extends BaseViewModel<QuestionsNavigator, FragmentCommonQuastionsBinding>
        implements RecyclerClick<Notification> {

    QuestionsAdapter questionsAdapter;
    boolean isRefreshing = false;
    boolean isRetry = false;
    boolean enableLoading = false;
    boolean isLoadMore = false;

    QuestionResponse questionResponse;
    public <V extends ViewDataBinding, N extends BaseNavigator> QuestionsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (QuestionsNavigator) navigation, (FragmentCommonQuastionsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        SessionManager.setIsThereNotification(false);
        ((MainActivity) getBaseActivity()).getViewDataBinding().appBarMain.drawerMainContent.bottomSheet.getMenu().getItem(2)
                .setIcon(getMyContext().getResources().getDrawable(R.drawable.ic_notification_nav));
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
        questionsAdapter = new QuestionsAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(questionsAdapter);
        questionsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (questionResponse != null &&
                        questionResponse.getMeta().getCurrentPage() < questionResponse.getMeta().getLastPage()) {
                    questionsAdapter.addItem(null);
                    questionsAdapter.notifyItemInserted(questionsAdapter.getItemCount() - 1);
                    getViewBinding().recyclerView.scrollToPosition(questionsAdapter.getItemCount() - 1);
                    setLoadMore(true);
                    getData(questionResponse.getMeta().getCurrentPage() + 1);
                }
            }
        });
    }

    public void getData(int page) {
        if (!isLoadMore() && !isRefreshing() && !isRetry()) {
            enableLoading = true;
        }
        getDataManager().getAppService().getQuestions(getMyContext(), true, page, new APICallBack<QuestionResponse>() {
            @Override
            public void onSuccess(QuestionResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    questionResponse = response;
                    questionsAdapter.addItems(response.getData());
                    notifiAdapter();
                } else {
                    onError(getMyContext().getResources().getString(R.string.no_data_available), 0);
                }
            }

            @Override
            public void onError(String error, int errorCode) {
                if (questionsAdapter.getItemCount() == 0) {
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
        getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.VISIBLE);

    }

    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                questionsAdapter.notifyDataSetChanged();
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
        } else if (isLoadMore()) {
            finishLoadMore();
        } else {
            enableLoading = false;
        }
    }

    public void finishLoadMore() {
        questionsAdapter.remove(questionsAdapter.getItemCount() - 1);
        questionsAdapter.notifyItemRemoved(questionsAdapter.getItemCount());
        questionsAdapter.setLoaded();
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
            questionsAdapter.clearItems();
            getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.GONE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            questionsAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
