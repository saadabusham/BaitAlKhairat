package com.saad.baitalkhairat.ui.intro.donors;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentDonorsBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.category.Category;
import com.saad.baitalkhairat.model.category.CategoryResponse;
import com.saad.baitalkhairat.model.news.News;
import com.saad.baitalkhairat.model.news.NewsResponse;
import com.saad.baitalkhairat.model.slider.Slider;
import com.saad.baitalkhairat.model.slider.SliderResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.CategoryAdapter;
import com.saad.baitalkhairat.ui.adapter.DotAdapter;
import com.saad.baitalkhairat.ui.adapter.NewsBarAdapter;
import com.saad.baitalkhairat.ui.adapter.SliderImageAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import java.util.ArrayList;
import java.util.List;

public class DonorsViewModel extends BaseViewModel<DonorsNavigator, FragmentDonorsBinding> implements RecyclerClick<Object> {

    CategoryAdapter categoryAdapter;
    boolean isRefreshing = false;
    boolean isRetry = false;
    ArrayList<Boolean> booleanArrayList;
    DotAdapter dotImageAdapter;
    int imagePosition = 0;

    SliderImageAdapter sliderImageAdapter;
    CountDownTimer countDownTimer;

    NewsResponse newsResponse;
    NewsBarAdapter newsBarAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> DonorsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (DonorsNavigator) navigation, (FragmentDonorsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
        setUpNewsBarRecycler();
        getSliders();
        getData();
        getNewsBar();
        getViewBinding().layoutNoDataFound.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRetring();
                getData();
            }
        });
    }

    private void setUpNewsBarRecycler() {
        getViewBinding().recyclerViewNewsBar.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.HORIZONTAL, false));
        getViewBinding().recyclerViewNewsBar.setItemAnimator(new DefaultItemAnimator());
        newsBarAdapter = new NewsBarAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerViewNewsBar.setAdapter(newsBarAdapter);
    }

    public void getNewsBar() {
        getDataManager().getAppService().getNews(getMyContext(), true, 1, new APICallBack<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null && response.getData().size() > 0) {
                    newsResponse = response;
                    newsBarAdapter.addItems(response.getData());
                    notifiAdapter();
                }
            }

            @Override
            public void onError(String error, int errorCode) {
            }
        });
    }

    private void getSliders() {
        getDataManager().getAppService().getSlider(getMyContext(), true, new APICallBack<SliderResponse>() {
            @Override
            public void onSuccess(SliderResponse response) {
                if (response.getData() != null && response.getData().size() > 0)
                    setUpViewPager(response.getData());
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    public void onViewAllClicked() {
        ((MainActivity) getBaseActivity()).moveBottomNavigation(R.id.nav_category);
    }

    public void setUpViewPager(List<Slider> slidersList) {
        ArrayList<Slider> arrayList = new ArrayList<>(slidersList);
        sliderImageAdapter = new SliderImageAdapter(getMyContext(), arrayList);
        getViewBinding().imageSlider.viewPager.setAdapter(sliderImageAdapter);
        booleanArrayList = getImageCountList(arrayList);
        getViewBinding().imageSlider.recyclerViewImagesDot.setBackgroundColor(getMyContext().getResources().getColor(android.R.color.transparent));
        getViewBinding().imageSlider.recyclerViewImagesDot.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.HORIZONTAL, false));
        dotImageAdapter = new DotAdapter(getMyContext(), booleanArrayList);
        getViewBinding().imageSlider.recyclerViewImagesDot.setAdapter(dotImageAdapter);
        getViewBinding().imageSlider.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
            }

            @Override
            public void onPageSelected(int position) {
                booleanArrayList.set(imagePosition, false);
                booleanArrayList.set(position, true);
                getViewBinding().imageSlider.recyclerViewImagesDot.post(() -> dotImageAdapter.notifyDataSetChanged());
                imagePosition = position;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer.start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
        getViewBinding().imageSlider.relativeSlider.setVisibility(View.VISIBLE);
        startSliderSliding(slidersList);
    }

    private void startSliderSliding(List<Slider> slidersList) {
        countDownTimer = new CountDownTimer(5000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getViewBinding().imageSlider.viewPager.setCurrentItem(
                        (getViewBinding().imageSlider.viewPager.getCurrentItem() + 1) <= (slidersList.size() - 1) ?
                                getViewBinding().imageSlider.viewPager.getCurrentItem() + 1 : 0, true);
                countDownTimer.start();
            }
        }.start();
    }

    private void setUpRecycler() {
        getViewBinding().swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIsRefreshing(true);
                getData();
            }
        });

        getViewBinding().recyclerView.setLayoutManager(new GridLayoutManager(getMyContext(), 3));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter = new CategoryAdapter(getMyContext(), this, getViewBinding().recyclerView);
        getViewBinding().recyclerView.setAdapter(categoryAdapter);

    }

    public ArrayList<Boolean> getImageCountList(ArrayList<Slider> imageArrayList) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        for (int i = 0; i < imageArrayList.size(); i++) {
            if (i == 0) {
                booleans.add(true);
            } else {
                booleans.add(false);
            }
        }
        return booleans;
    }

    public void getData() {
        if (!isRefreshing() && !isRetry()) {
            showLoading();
        }
        getDataManager().getDonorsService().getCategory(getMyContext(), true, new APICallBack<CategoryResponse>() {
            @Override
            public void onSuccess(CategoryResponse response) {
                checkIsLoadMoreAndRefreshing(true);
                if (response.getData() != null) {
                    if (response.getData().size() > 6) {
                        categoryAdapter.addItems(response.getData().subList(0, 6));
                    } else {
                        categoryAdapter.addItems(response.getData());
                    }
                    notifiAdapter();
                }

            }

            @Override
            public void onError(String error, int errorCode) {
                if (categoryAdapter.getItemCount() == 0) {
                    showNoDataFound();
                }
                if (categoryAdapter.getItemCount() == 0 && errorCode != 0) {
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
                categoryAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Object object, int position) {
        if (object instanceof Category) {
            Category category = (Category) object;
            Bundle data = new Bundle();
            data.putInt(AppConstants.BundleData.CATEGORY_ID, category.getValue());
            data.putString(AppConstants.BundleData.CATEGORY_NAME, category.getLabel());
            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_nav_home_to_cases_Fragment, data);
        } else if (object instanceof News) {
            News news = (News) object;
            Bundle data = new Bundle();
            data.putSerializable(AppConstants.BundleData.NEWS, news);
            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.notificationDetailsFragment, data);
        }
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
            hideLoading();
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
            categoryAdapter.clearItems();
            getViewBinding().layoutNoDataFound.relativeNoData.setVisibility(View.GONE);
            getViewBinding().swipeRefreshLayout.setEnabled(true);
        }
    }

    protected void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
            categoryAdapter.clearItems();
        }
        getViewBinding().swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }
}
