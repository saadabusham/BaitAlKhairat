package com.saad.baitalkhairat.ui.walletjourney.paymentholder;


import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.ui.base.BaseNavigator;

public interface PaymentHolderNavigator extends BaseNavigator {

    FragmentManager getChildFragment();

    Amount getAmount();
}
