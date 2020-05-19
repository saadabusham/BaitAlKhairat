package com.saad.baitalkhairat.ui.intro.home;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentHomeBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.intro.donors.DonorsFragment;
import com.saad.baitalkhairat.ui.intro.needy.NeedyFragment;

public class HomeViewModel extends BaseViewModel<HomeNavigator, FragmentHomeBinding> {

    FragmentManager fragmentManager;


    public <V extends ViewDataBinding, N extends BaseNavigator> HomeViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (HomeNavigator) navigation, (FragmentHomeBinding) viewDataBinding);
    }


    @Override
    protected void setUp() {
        fragmentManager = getBaseActivity().getSupportFragmentManager().getFragments().get(0).getChildFragmentManager();
        setFragment(0);
        setUpTablayout();
    }

    public void onDonorsClicked() {
        getViewBinding().btnDonors.setBackground(getMyContext()
                .getResources().getDrawable(R.drawable.ic_tab_active_right));

        getViewBinding().btnNeedy.setBackground(getMyContext()
                .getResources().getDrawable(R.drawable.ic_tab_anactive_left));
        getViewBinding().btnDonors.setTextColor(getMyContext().getResources().getColor(R.color.white));
        getViewBinding().btnNeedy.setTextColor(getMyContext().getResources().getColor(R.color.black));
        setFragment(0);
    }

    public void onNeedyClicked() {
        getViewBinding().btnDonors.setTextColor(getMyContext().getResources().getColor(R.color.black));
        getViewBinding().btnNeedy.setTextColor(getMyContext().getResources().getColor(R.color.white));
    }

    private void setFragment(int index) {
        fragmentManager.beginTransaction().replace(R.id.fragment_tab, getItem(index)).commit();
    }

    public void setUpTablayout() {
        getViewBinding().tabLayout.addTab(getViewBinding().tabLayout.newTab(), 0, true);
        getViewBinding().tabLayout.addTab(getViewBinding().tabLayout.newTab(), 1, false);
        getViewBinding().tabLayout.setSelectedTabIndicatorHeight(0);
        getViewBinding().tabLayout.getTabAt(0).setText(R.string.donors);
        getViewBinding().tabLayout.getTabAt(1).setText(R.string.needy);
        fragmentManager.beginTransaction().replace(R.id.fragment_tab, getItem(0)).commit();
        getViewBinding().tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    getViewBinding().tabLayout.setBackground(getMyContext().getResources().getDrawable(R.drawable.ic_tab_active_left));
                } else {
                    getViewBinding().tabLayout.setBackground(getMyContext().getResources().getDrawable(R.drawable.ic_tab_active_right));
                }
                fragmentManager.beginTransaction().replace(R.id.fragment_tab, getItem(tab.getPosition())).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
//        getNavigator().getChildFragment().popBackStack();

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
