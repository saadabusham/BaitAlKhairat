package com.saad.baitalkhairat.ui.auth.signinholder;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentSigninHolderBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.auth.loginJourney.login.LoginFragment;
import com.saad.baitalkhairat.ui.auth.registerJourney.register.RegisterFragment;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class SignInHolderViewModel extends BaseViewModel<SignInHolderNavigator, FragmentSigninHolderBinding> {

    LoginFragment loginFragment = new LoginFragment();
    RegisterFragment registerFragment = new RegisterFragment();

    public <V extends ViewDataBinding, N extends BaseNavigator> SignInHolderViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (SignInHolderNavigator) navigation, (FragmentSigninHolderBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        setUpTablayout();
    }

    public void setUpTablayout() {

        getViewBinding().tabLayout.addTab(getViewBinding().tabLayout.newTab(), 0, true);
        getViewBinding().tabLayout.addTab(getViewBinding().tabLayout.newTab(), 1, false);
        getViewBinding().tabLayout.setSelectedTabIndicatorColor(getMyContext().getResources().getColor(R.color.orange));
        getViewBinding().tabLayout.setTabTextColors(R.color.black, R.color.black);
        getViewBinding().tabLayout.getTabAt(0).setText(R.string.login);
        getViewBinding().tabLayout.getTabAt(1).setText(R.string.register);
        FragmentManager fragmentManager = getBaseActivity().getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_sign_in, getItem(getNavigator().getSignType())).commit();
        getViewBinding().tabLayout.getTabAt(getNavigator().getSignType()).select();
        getViewBinding().tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_sign_in, getItem(tab.getPosition())).commit();
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
                loginFragment.setArguments(bundle);
                return loginFragment;
            default:

                registerFragment.setArguments(bundle);
                return registerFragment;
        }
    }
}
