package com.saad.baitalkhairat.ui.emptyactivity;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;

import com.saad.baitalkhairat.databinding.ActivityEmptyBinding;
import com.saad.baitalkhairat.model.DataExample;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.RequestFactory;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmptyActivityViewModel extends BaseViewModel<EmptyActivityNavigator, ActivityEmptyBinding> {


    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<List<DataExample>> dataExampleList;

    public <V extends ViewDataBinding, N extends BaseNavigator> EmptyActivityViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (EmptyActivityNavigator) navigation, (ActivityEmptyBinding) viewDataBinding);
        isLoading = new MutableLiveData<>();
        dataExampleList = new MutableLiveData<>();
    }


    MutableLiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    MutableLiveData<List<DataExample>> getData() {
        return dataExampleList;
    }

    private void setData(List<DataExample> dataExampleList) {
        this.dataExampleList.postValue(dataExampleList);
    }

    void loadData() {
        getDataFromApi();
    }

    void onEmptyClicked() {
        setData(Collections.emptyList());
    }

    private void setIsLoading(boolean loading) {
        isLoading.postValue(loading);
    }

    private void getDataFromApi() {
        new RequestFactory(getMyContext(), true, new APICallBack<ArrayList<DataExample>>() {
            @Override
            public void onSuccess(ArrayList<DataExample> response) {
                setData(response);
            }

            @Override
            public void onError(String error, int errorCode) {
                setData(Collections.emptyList());
                ((EmptyActivity) getMyContext()).showToast(error);
            }

        }).getDataFromApi(getDataManager().getDataService().getDataApi().getAllData());
    }

    public void onBtnClicked() {
        getViewBinding().btn.setText("clicked");
    }

    @Override
    protected void setUp() {

    }
}
