package com.saad.baitalkhairat.ui.profilejourney.userdegree;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentUserDegreeBinding;
import com.saad.baitalkhairat.enums.PhoneNumberTypes;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

public class UserDegreeViewModel extends BaseViewModel<UserDegreeNavigator, FragmentUserDegreeBinding> {


    boolean isStartDateClicked = true;
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            isValid();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
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

        if (isStartDateClicked) {
            getViewBinding().edStartDay.setText(String.valueOf(dayOfMonth));
            getViewBinding().edStartMonth.setText(String.valueOf(monthOfYear + 1));
            getViewBinding().edStartYear.setText(String.valueOf(year));
        } else {
            getViewBinding().edFinishDay.setText(String.valueOf(dayOfMonth));
            getViewBinding().edFinishMonth.setText(String.valueOf(monthOfYear + 1));
            getViewBinding().edFinishYear.setText(String.valueOf(year));
        }
        isValid();
    };

    public <V extends ViewDataBinding, N extends BaseNavigator> UserDegreeViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (UserDegreeNavigator) navigation, (FragmentUserDegreeBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpSpinnerCountry();
        setUpWatcher();
    }

    private void setUpWatcher() {
        getViewBinding().edSchoolUniversity.addTextChangedListener(textWatcher);
        getViewBinding().edSpecialization.addTextChangedListener(textWatcher);
        getViewBinding().spinnerStudyCountry.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void setUpSpinnerCountry() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.country_original));
        arrayList.add(getMyContext().getResources().getString(R.string.jordan));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerStudyCountry.setAdapter(adapter);
    }

    public void openDatePicker(boolean isStartDateClicked) {
        this.isStartDateClicked = isStartDateClicked;
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
//        getDataManager().getAuthService().getDataApi().registerUser(getUserObj())
//                .toObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new CustomObserverResponse<RegisterResponse>(getMyContext(), true, new APICallBack<RegisterResponse>() {
//                    @Override
//                    public void onSuccess(RegisterResponse response) {
//                        User user = response.getUser();
//                        user.setToken(response.getJwt_token());
//                        User.getInstance().setObjUser(user);
//                        SessionManager.createUserLoginSession();
////                        getDataManager().getAuthervice().setObjNull();
//                        getDataManager().getAuthService().updateFirebaseToken(getMyContext(), true, new APICallBack() {
//                            @Override
//                            public void onSuccess(Object response) {
//                                getBaseActivity().finishAffinity();
////                                getBaseActivity().startActivity(MainActivity.newIntent(getMyContext()));
//                            }
//
//                            @Override
//                            public void onError(String error, int errorCode) {
//                                showToast(error);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(String error, int errorCode) {
//                        showToast(error);
//                    }
//                }));
    }

    private User getUserObj() {
        User user = User.getInstance();
        return user;
    }

    private void checkValidate(boolean isValid) {
        if (isValid) {
            getViewBinding().btnSave.setBackgroundColor(getMyContext().getResources().getColor(R.color.orange_login_button));
            getViewBinding().btnSave.setTextColor(getMyContext().getResources().getColor(R.color.white));
            getViewBinding().btnSave.setEnabled(true);
        } else {
            getViewBinding().btnSave.setBackgroundColor(getMyContext().getResources().getColor(R.color.tablayout_gray));
            getViewBinding().btnSave.setTextColor(getMyContext().getResources().getColor(R.color.login_text_gray));
            getViewBinding().btnSave.setEnabled(false);
        }
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(getMyContext()).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }

    public boolean isValid() {
        int error = 0;

        if (getViewBinding().edSchoolUniversity.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edSchoolUniversity.setError(getMyContext().getString(R.string.school_university_is_required));
        }

        if (getViewBinding().edSpecialization.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edSpecialization.setError(getMyContext().getString(R.string.specialization_is_required));
        }

        if (getViewBinding().edStartDay.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edStartDay.setError(getMyContext().getString(R.string.start_date_is_required));
        }

        if (getViewBinding().edFinishDay.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edFinishDay.setError(getMyContext().getString(R.string.graduation_day_is_required));
        }

        if (getViewBinding().spinnerStudyCountry.getSelectedItemPosition() == 0) {
            error = +1;
        }

        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);
        return error == 0;
    }
}
