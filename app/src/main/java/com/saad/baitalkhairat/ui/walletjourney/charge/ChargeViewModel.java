package com.saad.baitalkhairat.ui.walletjourney.charge;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentChargeBinding;
import com.saad.baitalkhairat.enums.ChargeToTypes;
import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.model.wallet.CheckCharge;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;

import java.util.ArrayList;

public class ChargeViewModel extends BaseViewModel<ChargeNavigator, FragmentChargeBinding> {
    ArrayList<String> arrayList = new ArrayList<>();

    public <V extends ViewDataBinding, N extends BaseNavigator> ChargeViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ChargeNavigator) navigation, (FragmentChargeBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpSpinnerCountry();
        setUpLayout();

        getViewBinding().edAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setUpSpinnerCountry() {

        arrayList.add(getMyContext().getResources().getString(R.string.jod));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountryCurrency.setAdapter(adapter);
    }

    public void setUpLayout() {
        ChargeToTypes chargeToTypes = ChargeToTypes.fromInt(getNavigator().getChargeTo());
        switch (chargeToTypes) {
            case MY_WALLET:
                getViewBinding().imgIcon.setImageResource(R.drawable.ic_coin);
                getViewBinding().tvTitle.setText(R.string.charge_your_wallet_to_donate);
                getViewBinding().tvSubtitle.setText(R.string.the_amount_to_be_charged_in_my_wallet);
                getViewBinding().tvMessage.setText(R.string.this_amount_will_be_added_to_your_portfolio_and_can_be_donated_at_any_time_to_any_needy_person);
                break;
            case BAIT_AL_KHAIRAT_ACCOUNT:
                getViewBinding().imgIcon.setImageResource(R.drawable.ic_logo);
                getViewBinding().tvTitle.setText(R.string.charge_to_bait_al_khairat);
                getViewBinding().tvSubtitle.setText(R.string.the_amount_to_be_charged_to_bait_al_khairatt);
                getViewBinding().tvMessage.setText(R.string.this_amount_will_be_added_to_the_House_of_Goodwill_account_and_the_House_of_Goodwill_will_dispose_of_this_amount_for_the_needy);
                break;
        }
    }

    public void onChargeClick() {
        checkCharge();
    }

    private void checkCharge() {
        getDataManager().getWalletService().checkCharge(getMyContext(),
                getNavigator().getChargeTo() == ChargeToTypes.MY_WALLET.getType() ? 1 : 2
                , Double.valueOf(getViewBinding().edAmount.getText().toString()),
                new APICallBack<CheckCharge>() {
                    @Override
                    public void onSuccess(CheckCharge response) {
                        Bundle data = new Bundle();
                        data.putInt(AppConstants.BundleData.CHARGE_TO_TYPES, getNavigator().getChargeTo());
                        Amount amount = new Amount(getViewBinding().edAmount.getText().toString(),
                                arrayList.get(getViewBinding().spinnerCountryCurrency.getSelectedItemPosition()));
                        data.putSerializable(AppConstants.BundleData.AMOUNT, amount);
                        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.action_chargeFragment_to_paymentHolderFragment, data);
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showErrorSnackBar(error);
                    }
                });
    }

    public boolean isValid() {
        int error = 0;
        if (getViewBinding().edAmount.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edAmount.setError(getMyContext().getString(R.string.amount_is_required));
        }
        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);
        return error == 0;
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnCharge.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnCharge.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnCharge.setEnabled(true);
        } else {
            getViewBinding().btnCharge.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnCharge.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnCharge.setEnabled(false);
        }
    }
}
