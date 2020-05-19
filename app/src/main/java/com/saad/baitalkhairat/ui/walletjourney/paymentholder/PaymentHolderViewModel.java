package com.saad.baitalkhairat.ui.walletjourney.paymentholder;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentPaymentHolderBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.walletjourney.banktransfer.BankTransferFragment;
import com.saad.baitalkhairat.ui.walletjourney.creditcard.CreditCardFragment;
import com.saad.baitalkhairat.utils.AppConstants;

public class PaymentHolderViewModel extends BaseViewModel<PaymentHolderNavigator, FragmentPaymentHolderBinding> {

    BankTransferFragment bankTransferFragment = new BankTransferFragment();
    CreditCardFragment creditCardFragment = new CreditCardFragment();

    public <V extends ViewDataBinding, N extends BaseNavigator> PaymentHolderViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (PaymentHolderNavigator) navigation, (FragmentPaymentHolderBinding) viewDataBinding);

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
        getViewBinding().tabLayout.getTabAt(0).setText(R.string.bank_transfer);
        getViewBinding().tabLayout.getTabAt(1).setText(R.string.credit_card);
        FragmentManager fragmentManager = getBaseActivity().getSupportFragmentManager().getPrimaryNavigationFragment().getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_payment, getItem(0)).commit();
        getViewBinding().tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_payment, getItem(tab.getPosition())).commit();
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
                bundle.putSerializable(AppConstants.BundleData.AMOUNT, getNavigator().getAmount());
                bankTransferFragment.setArguments(bundle);
                return bankTransferFragment;
            default:
                bundle.putSerializable(AppConstants.BundleData.AMOUNT, getNavigator().getAmount());
                creditCardFragment.setArguments(bundle);
                return creditCardFragment;
        }
    }
}
