package com.saad.baitalkhairat.ui.needjourney.myneedsholder;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyNeedsHolderBinding;
import com.saad.baitalkhairat.enums.MyNeedsTabTypes;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.needjourney.myneedslist.MyNeedsListFragment;
import com.saad.baitalkhairat.utils.AppConstants;

public class MyNeedsHolderViewModel extends BaseViewModel<MyNeedsHolderNavigator, FragmentMyNeedsHolderBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> MyNeedsHolderViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MyNeedsHolderNavigator) navigation, (FragmentMyNeedsHolderBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        setUpTablayout();
    }

    public void setUpTablayout() {
        getViewBinding().tabLayout.addTab(getViewBinding().tabLayout.newTab(), 0, true);
        getViewBinding().tabLayout.addTab(getViewBinding().tabLayout.newTab(), 1, false);
        getViewBinding().tabLayout.setSelectedTabIndicatorColor(getMyContext().getResources().getColor(R.color.black));
        getViewBinding().tabLayout.setTabTextColors(R.color.black, R.color.black);
        getViewBinding().tabLayout.getTabAt(0).setText(R.string.current_needs);
        getViewBinding().tabLayout.getTabAt(1).setText(R.string.past_needs);
        FragmentManager fragmentManager = getBaseActivity().getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_my_needs, getItem(0)).commit();
        getViewBinding().tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_my_needs, getItem(tab.getPosition())).commit();
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
        switch (position) {
            case 0:
                bundle.putInt(AppConstants.BundleData.MY_NEEDS_TAB_TYPE, MyNeedsTabTypes.CURRENT.getType());
                MyNeedsListFragment currentNeedsFragment = new MyNeedsListFragment();
                currentNeedsFragment.setArguments(bundle);
                return currentNeedsFragment;
            default:
                bundle.putInt(AppConstants.BundleData.MY_NEEDS_TAB_TYPE, MyNeedsTabTypes.HISTORY.getType());
                MyNeedsListFragment historyNeedsFragment = new MyNeedsListFragment();
                historyNeedsFragment.setArguments(bundle);
                return historyNeedsFragment;
        }
    }

    public void onApplyNeedClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_myNeedsHolderFragment_to_applyNeedsFragment);
    }
}
