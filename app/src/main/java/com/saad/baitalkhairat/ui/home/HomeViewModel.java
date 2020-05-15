package com.saad.baitalkhairat.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentHomeBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.donors.DonorsFragment;
import com.saad.baitalkhairat.ui.needy.NeedyFragment;

public class HomeViewModel extends BaseViewModel<HomeNavigator, FragmentHomeBinding> {

    FragmentManager fragmentManager;


    public <V extends ViewDataBinding, N extends BaseNavigator> HomeViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (HomeNavigator) navigation, (FragmentHomeBinding) viewDataBinding);
    }


    @Override
    protected void setUp() {
        fragmentManager = getBaseActivity().getSupportFragmentManager().getFragments().get(0).getChildFragmentManager();
        setFragment(0);
    }

    public void onDonorsClicked() {
        getViewBinding().btnDonors.setBackground(getMyContext()
                .getResources().getDrawable(R.drawable.shape_rounded_tab_orange_right));

        getViewBinding().btnNeedy.setBackground(getMyContext()
                .getResources().getDrawable(R.drawable.shape_rounded_tab_empty_orange_left));
        getViewBinding().btnDonors.setTextColor(getMyContext().getResources().getColor(R.color.white));
        getViewBinding().btnNeedy.setTextColor(getMyContext().getResources().getColor(R.color.black));
        setFragment(0);
    }

    public void onNeedyClicked() {
        getViewBinding().btnNeedy.setBackground(getMyContext()
                .getResources().getDrawable(R.drawable.shape_rounded_tab_orange_left));

        getViewBinding().btnDonors.setBackground(getMyContext()
                .getResources().getDrawable(R.drawable.shape_rounded_tab_empty_orange_right));
        getViewBinding().btnDonors.setTextColor(getMyContext().getResources().getColor(R.color.black));
        getViewBinding().btnNeedy.setTextColor(getMyContext().getResources().getColor(R.color.white));
        setFragment(1);
    }

    private void setFragment(int index) {
        fragmentManager.beginTransaction().replace(R.id.fragment_tab, getItem(index)).commit();
    }

    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        getNavigator().getChildFragment().popBackStack();

        switch (position) {
            case 0:
                DonorsFragment donorsFragment = new DonorsFragment();
                donorsFragment.setArguments(bundle);
                return donorsFragment;
            default:
                NeedyFragment needyFragment = new NeedyFragment();
                needyFragment.setArguments(bundle);
                return needyFragment;
        }
    }

}
