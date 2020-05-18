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
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.AppConstants;

import java.util.ArrayList;

public class FilterCasesViewModel extends BaseViewModel<FilterCasesNavigator, FragmentFilterCasesBinding> {

    boolean canceled = false;

    public <V extends ViewDataBinding, N extends BaseNavigator> FilterCasesViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (FilterCasesNavigator) navigation, (FragmentFilterCasesBinding) viewDataBinding);

    }

    public void onFilterClick() {
        popUp();
    }

    public void returnData() {
        if (!canceled) {
            Intent data = new Intent();
            data.putExtra(AppConstants.BundleData.FILTER, new Filter());
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
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.gender));
        arrayList.add(getMyContext().getResources().getString(R.string.male));
        arrayList.add(getMyContext().getResources().getString(R.string.female));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerGender.setAdapter(adapter);
    }

    private void setUpSpinnerCountry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.country_original));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountry.setAdapter(adapter);
    }
}
