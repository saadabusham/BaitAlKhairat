package com.saad.baitalkhairat.ui.profilejourney.account;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentAccountBinding;
import com.saad.baitalkhairat.enums.SignTypes;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class AccountViewModel extends BaseViewModel<AccountNavigator, FragmentAccountBinding> {


    public <V extends ViewDataBinding, N extends BaseNavigator> AccountViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (AccountNavigator) navigation, (FragmentAccountBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        getViewBinding().layoutUserNotRegister.setViewModel(this);
        if (!SessionManager.isLoggedIn())
            getViewBinding().layoutUserNotRegister.relativeUserNotRegistered.setVisibility(View.VISIBLE);
        else
            getViewBinding().layoutUserNotRegister.relativeUserNotRegistered.setVisibility(View.GONE);
    }

    public void onLoginClicked() {
        navigateToLoginRegister(SignTypes.LOGIN.getType());
    }

    public void onRegisterClicked() {
        navigateToLoginRegister(SignTypes.REGISTER.getType());
    }

    private void navigateToLoginRegister(int toLogin) {
        Bundle data = new Bundle();
        data.putInt("type", toLogin);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_nav_account_to_signInHolderFragment, data);
    }

}
