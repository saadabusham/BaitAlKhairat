package com.saad.baitalkhairat.ui.settings;

import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentSettingsBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.adapter.SettingsAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.ui.splashscreen.SplashScreenActivity;

import java.util.ArrayList;

public class SettingsViewModel extends BaseViewModel<SettingsNavigator, FragmentSettingsBinding> implements RecyclerClickNoData {


    public <V extends ViewDataBinding, N extends BaseNavigator> SettingsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (SettingsNavigator) navigation, (FragmentSettingsBinding) viewDataBinding);
    }

    private void setUpRecycler() {
        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getMyContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getMyContext(), R.drawable.divider));
        getViewBinding().recyclerView.addItemDecoration(itemDecorator);
        getViewBinding().recyclerView.setAdapter(new SettingsAdapter(getMyContext(), this, getArraylistImages(), getArraylistText()));
    }

    private ArrayList<Integer> getArraylistImages() {
        ArrayList<Integer> arrayListImages = new ArrayList<>();
        arrayListImages.add(R.drawable.ic_account);
        arrayListImages.add(R.drawable.ic_global);
        arrayListImages.add(R.drawable.ic_assignment);
        arrayListImages.add(R.drawable.ic_privacy_policy);
        arrayListImages.add(R.drawable.ic_info);
        arrayListImages.add(R.drawable.ic_logout);
        return arrayListImages;
    }

    private ArrayList<Integer> getArraylistText() {
        ArrayList<Integer> arrayListText = new ArrayList<>();
        arrayListText.add(R.string.edit_profile);
        arrayListText.add(R.string.change_language);
        arrayListText.add(R.string.about_us);
        arrayListText.add(R.string.privacy_policy);
        arrayListText.add(R.string.terms_and_conditions);
        arrayListText.add(R.string.logout);
        return arrayListText;
    }


    @Override
    public void onClick(int position) {
        switch (position) {

        }

    }

    public void showLogoutDialog() {
        new OnLineDialog(getMyContext()) {
            @Override
            public void onPositiveButtonClicked() {
                dismiss();
                logout();
            }

            @Override
            public void onNegativeButtonClicked() {
                dismiss();
            }
        }.showConfirmationDialog(DialogTypes.OK_CANCEL, getMyContext().getString(R.string.logout),
                getMyContext().getString(R.string.logout_text));
    }

    @Override
    protected void setUp() {
        setUpRecycler();
    }

    private void logout() {
        getDataManager().getAuthService().logout(getMyContext(), new APICallBack() {
            @Override
            public void onSuccess(Object response) {
                SessionManager.logoutUser();
                getBaseActivity().finishAffinity();
                getBaseActivity().startActivity(new Intent(getMyContext(),
                        SplashScreenActivity.class));
            }

            @Override
            public void onError(String error, int errorCode) {
                showToast(error);
            }
        });
    }
}
