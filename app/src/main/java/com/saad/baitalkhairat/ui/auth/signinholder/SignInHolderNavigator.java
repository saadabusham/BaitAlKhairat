package com.saad.baitalkhairat.ui.auth.signinholder;


import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.ui.base.BaseNavigator;

public interface SignInHolderNavigator extends BaseNavigator {

    int getSignType();

    FragmentManager getChildFragment();
}
