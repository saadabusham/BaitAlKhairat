package com.saad.baitalkhairat.ui.auth.registerJourney.register;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentRegisterBinding;
import com.saad.baitalkhairat.enums.PhoneNumberTypes;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.RegisterResponse;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator, FragmentRegisterBinding> {



    public <V extends ViewDataBinding, N extends BaseNavigator> RegisterViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (RegisterNavigator) navigation, (FragmentRegisterBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpSpinnerGender();
        setUpSpinnerCountry();
        setUpSpinnerCountryCode();
        setUpWatcher();
    }

    private void setUpWatcher() {
        getViewBinding().edUserName.addTextChangedListener(textWatcher);
        getViewBinding().edPhoneNumber.addTextChangedListener(textWatcher);
        getViewBinding().edEmail.addTextChangedListener(textWatcher);
        getViewBinding().edPassword.addTextChangedListener(textWatcher);
        getViewBinding().edConfirmPassword.addTextChangedListener(textWatcher);
        getViewBinding().spinnerGender.setOnItemSelectedListener(onItemSelectedListener);
        getViewBinding().spinnerCountry.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void setUpSpinnerGender() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.gender));
        arrayList.add(getMyContext().getResources().getString(R.string.male));
        arrayList.add(getMyContext().getResources().getString(R.string.female));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerGender.setAdapter(adapter);
    }

    private void setUpSpinnerCountry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.country_original));
        arrayList.add(getMyContext().getResources().getString(R.string.jordan));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountry.setAdapter(adapter);
    }

    private void setUpSpinnerCountryCode() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.jordan_code));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountryCode.setAdapter(adapter);
    }

    public void openDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(getMyContext(), AlertDialog.THEME_HOLO_LIGHT, date,
                Calendar.YEAR, Calendar.MONTH,
                Calendar.DAY_OF_MONTH);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1950);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        dialog.show();
    }

    public void registerClicked() {
        if (isValid()) {
            Bundle data = new Bundle();
            data.putInt("type", PhoneNumberTypes.REGISTER.getValue());
            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.otpVerifierFragment, data);
//            registerUser();
        }
    }

    public void registerUser() {
        getDataManager().getAuthService().getDataApi().registerUser(getUserObj())
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<RegisterResponse>(getMyContext(), true, new APICallBack<RegisterResponse>() {
                    @Override
                    public void onSuccess(RegisterResponse response) {
                        User user = response.getUser();
                        user.setToken(response.getJwt_token());
                        User.getInstance().setObjUser(user);
                        SessionManager.createUserLoginSession();
//                        getDataManager().getAuthervice().setObjNull();
                        getDataManager().getAuthService().updateFirebaseToken(getMyContext(), true, new APICallBack() {
                            @Override
                            public void onSuccess(Object response) {
                                getBaseActivity().finishAffinity();
//                                getBaseActivity().startActivity(MainActivity.newIntent(getMyContext()));
                            }

                            @Override
                            public void onError(String error, int errorCode) {
                                showToast(error);
                            }
                        });
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showToast(error);
                    }
                }));
    }

    private User getUserObj() {
        User user = User.getInstance();
        user.setEmail(getViewBinding().edEmail.getText().toString().trim());
        user.setName(getViewBinding().edUserName.getText().toString());
        user.setPassword(getViewBinding().edPassword.getText().toString());
        user.setPassword_confirmation(getViewBinding().edConfirmPassword.getText().toString());
        return user;
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            isValid();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnSignup.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnSignup.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnSignup.setEnabled(true);
        } else {
            getViewBinding().btnSignup.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnSignup.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnSignup.setEnabled(false);
        }
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }

    TextWatcher textWatcher = new TextWatcher() {
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
    };
    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {

        getViewBinding().edDay.setText(String.valueOf(dayOfMonth));
        getViewBinding().edMonth.setText(String.valueOf(monthOfYear + 1));
        getViewBinding().edYear.setText(String.valueOf(year));
        isValid();
    };

    public boolean isValid() {
        int error = 0;

        if (!Patterns.EMAIL_ADDRESS.matcher(getViewBinding().edEmail.getText().toString().trim()).matches()) {
            error = +1;
            getViewBinding().edEmail.setError(getMyContext().getString(R.string.email_is_required));
        }

        if (getViewBinding().edUserName.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edUserName.setError(getMyContext().getString(R.string.user_name_is_required));
        }

        if (getViewBinding().edPhoneNumber.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edPhoneNumber.setError(getMyContext().getString(R.string.the_phone_number_is_required));
        }

        if (getViewBinding().edDay.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edDay.setError(getMyContext().getString(R.string.birthday_is_required));
        }

        if (getViewBinding().spinnerCountry.getSelectedItemPosition() == 0) {
            error = +1;
        }

        if (getViewBinding().spinnerGender.getSelectedItemPosition() == 0) {
            error = +1;
        }

        if (getViewBinding().edPassword.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edPassword.setError(getMyContext().getString(R.string.password_is_required));
        }


        if (!getViewBinding().edPassword.getText().toString().equals(getViewBinding().edConfirmPassword.getText().toString())) {
            error = +1;
            getViewBinding().edPassword.setError(getMyContext().getString(R.string.does_not_match));
            getViewBinding().edConfirmPassword.setError(getMyContext().getString(R.string.does_not_match));
        }

        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);
        return error == 0;
    }
}
