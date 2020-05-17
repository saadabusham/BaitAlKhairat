package com.saad.baitalkhairat.ui.profilejourney.account;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentAccountBinding;
import com.saad.baitalkhairat.enums.SignTypes;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.AccountItem;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.AccountItemsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;

public class AccountViewModel extends BaseViewModel<AccountNavigator, FragmentAccountBinding>
        implements RecyclerClickNoData {

    AccountItemsAdapter accountItemsAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> AccountViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (AccountNavigator) navigation, (FragmentAccountBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        getViewBinding().layoutUserNotRegister.setViewModel(this);
//        if (!SessionManager.isLoggedIn())
//            getViewBinding().layoutUserNotRegister.relativeUserNotRegistered.setVisibility(View.VISIBLE);
//        else
//            getViewBinding().layoutUserNotRegister.relativeUserNotRegistered.setVisibility(View.GONE);
        setUpRecycler();
    }

    private void setUpRecycler() {
        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        accountItemsAdapter = new AccountItemsAdapter(getMyContext(), getAccountItems(), this);
        getViewBinding().recyclerView.setAdapter(accountItemsAdapter);
    }

    private ArrayList<AccountItem> getAccountItems() {
        ArrayList<AccountItem> accountItems = new ArrayList<>();
        accountItems.add(new AccountItem(R.string.my_needs, R.drawable.ic_needy));
        accountItems.add(new AccountItem(R.string.my_donations, R.drawable.ic_donor));
        accountItems.add(new AccountItem(R.string.my_wallet, R.drawable.ic_coin));
        return accountItems;
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 0:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_nav_account_to_myNeedsHolderFragment);
                break;
        }
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

    public void onEditClick() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_nav_account_to_myInfoListFragment);
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }
}
