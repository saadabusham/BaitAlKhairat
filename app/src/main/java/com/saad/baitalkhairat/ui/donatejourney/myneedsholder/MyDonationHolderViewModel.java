package com.saad.baitalkhairat.ui.donatejourney.myneedsholder;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyDonationHolderBinding;
import com.saad.baitalkhairat.enums.MyNeedsTabTypes;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.donatejourney.myneedslist.MyDonationListFragment;
import com.saad.baitalkhairat.utils.AppConstants;

public class MyDonationHolderViewModel extends BaseViewModel<MyDonationHolderNavigator, FragmentMyDonationHolderBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> MyDonationHolderViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MyDonationHolderNavigator) navigation, (FragmentMyDonationHolderBinding) viewDataBinding);

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
        getViewBinding().tabLayout.getTabAt(0).setText(R.string.current_donors);
        getViewBinding().tabLayout.getTabAt(1).setText(R.string.past_donors);
        FragmentManager fragmentManager = getBaseActivity().getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_my_donors, getItem(0)).commit();
        getViewBinding().tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_my_donors, getItem(tab.getPosition())).commit();
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
                MyDonationListFragment currentDonorsFragment = new MyDonationListFragment();
                currentDonorsFragment.setArguments(bundle);
                return currentDonorsFragment;
            default:
                bundle.putInt(AppConstants.BundleData.MY_NEEDS_TAB_TYPE, MyNeedsTabTypes.HISTORY.getType());
                MyDonationListFragment historyDonorsFragment = new MyDonationListFragment();
                historyDonorsFragment.setArguments(bundle);
                return historyDonorsFragment;
        }
    }

    public void onApplyDonationClick() {
        Bundle data = new Bundle();
        data.putInt(AppConstants.BundleData.CATEGORY_ID, 0);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_myDonationHolderFragment_to_casesFragment, data);
    }
}
