package com.saad.baitalkhairat.ui.intro.filtercases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentFilterCasesBinding;
import com.saad.baitalkhairat.model.Filter;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.AppConstants;

import java.util.ArrayList;

public class FilterCasesViewModel extends BaseViewModel<FilterCasesNavigator, FragmentFilterCasesBinding> {

    boolean canceled = false;
    ArrayList<ListItem> countryNameList = new ArrayList<>();
    ArrayAdapter<ListItem> countryNameAdapter;

    ArrayList<ListItem> genderList = new ArrayList<>();
    ArrayAdapter<ListItem> genderAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> FilterCasesViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (FilterCasesNavigator) navigation, (FragmentFilterCasesBinding) viewDataBinding);

    }

    public void onFilterClick() {
        popUp();
    }

    public void returnData() {
        if (!canceled) {
            Intent data = new Intent();
            Filter filter = new Filter();
            if (getViewBinding().spinnerGender.getSelectedItemPosition() > 0) {
                filter.setGender(genderAdapter.getItem(getViewBinding().spinnerGender.getSelectedItemPosition()).getValue());
            }
            if (getViewBinding().spinnerCountry.getSelectedItemPosition() > 0) {
                filter.setCountry(countryNameAdapter.getItem(getViewBinding().spinnerCountry.getSelectedItemPosition()).getValue());
            }
            data.putExtra(AppConstants.BundleData.FILTER, filter);
            ((MainActivity) getBaseActivity()).onActivityResultFromFragment(
                    1, Activity.RESULT_OK, data);
        }
    }

    @Override
    protected void setUp() {
        if (getNavigator().filterWithCategory()) {
            setUpSpinnerCaseType();
            getViewBinding().spinnerCaseType.setVisibility(View.VISIBLE);
        }
        setUpSpinnerGender();
        setUpSpinnerCountry();
    }

    private void setUpSpinnerCaseType() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.case_type_original));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCaseType.setAdapter(adapter);
    }

    private void setUpSpinnerGender() {
        genderList.add(new ListItem(getMyContext().getResources().getString(R.string.gender)));
        genderAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, genderList);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerGender.setAdapter(genderAdapter);
        getGenders();
    }

    private void getGenders() {
        getDataManager().getAppService().getGenders(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                genderAdapter.addAll(response.getList());
                genderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void setUpSpinnerCountry() {
        countryNameList.add(new ListItem(getMyContext().getResources().getString(R.string.country_original)));
        countryNameAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, countryNameList);
        countryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountry.setAdapter(countryNameAdapter);

        getCountryNames();
    }

    private void getCountryNames() {
        getDataManager().getAppService().getCountryName(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                countryNameAdapter.addAll(response.getList());
                countryNameAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }
}
