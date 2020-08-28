package com.saad.baitalkhairat.ui.profilejourney.userdegree;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentUserDegreeBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.ProfileResponse;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.user.UserResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.utils.LanguageUtils;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserDegreeViewModel extends BaseViewModel<UserDegreeNavigator, FragmentUserDegreeBinding> {

    ArrayList<ListItem> countryNameList = new ArrayList<>();
    ArrayAdapter<ListItem> countryNameAdapter;

    boolean isStartDateClicked = true;
    String bindingKey = "";
    String start_date = "", end_date = "";

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
            start_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        } else {
            getViewBinding().edFinishDay.setText(String.valueOf(dayOfMonth));
            getViewBinding().edFinishMonth.setText(String.valueOf(monthOfYear + 1));
            getViewBinding().edFinishYear.setText(String.valueOf(year));
            end_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        }
        isValid();
    };

    public <V extends ViewDataBinding, N extends BaseNavigator> UserDegreeViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (UserDegreeNavigator) navigation, (FragmentUserDegreeBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        bindingKey = GeneralFunction.generateUUID();
        getViewBinding().setData(getNavigator().getUser());
        setUpSpinnerCountry();
        setUpWatcher();
    }

    private void setUpWatcher() {
        getViewBinding().edSchoolUniversity.addTextChangedListener(textWatcher);
        getViewBinding().edSpecialization.addTextChangedListener(textWatcher);
        getViewBinding().spinnerStudyCountry.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void setUpSpinnerCountry() {
        countryNameList.add(new ListItem(getMyContext().getResources().getString(R.string.country_original)));
        countryNameAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, countryNameList);
        countryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerStudyCountry.setAdapter(countryNameAdapter);

        getCountryNames();
    }

    private void getCountryNames() {
        getDataManager().getAppService().getCountryName(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                countryNameAdapter.addAll(response.getList());
                countryNameAdapter.notifyDataSetChanged();
                getViewBinding().spinnerStudyCountry.setSelection(getNavigator().getUser().getStudyCountry(response.getList()));
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
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

    public void onSaveClick() {
        if (isValid()) {
            getDataManager().getAuthService().getDataApi().updateProfile(getUserObj())
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CustomObserverResponse<ProfileResponse>(getMyContext(), true, new APICallBack<ProfileResponse>() {
                        @Override
                        public void onSuccess(ProfileResponse response) {
                            new OnLineDialog(getMyContext()) {
                                @Override
                                public void onPositiveButtonClicked() {
                                    dismiss();
                                    Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                            .navigate(R.id.action_userDegreeFragment_to_nav_account);
                                }

                                @Override
                                public void onNegativeButtonClicked() {

                                }
                            }.showConfirmationDialog(DialogTypes.OK, getMyContext().getResources().getString(R.string.update_successfully),
                                    getMyContext().getResources().getString(R.string.your_profile_has_been_updated_successfully));
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            showSnackBar(getMyContext().getString(R.string.error),
                                    error, getMyContext().getResources().getString(R.string.OK),
                                    new SnackViewBulider.SnackbarCallback() {
                                        @Override
                                        public void onActionClick(Snackbar snackbar) {
                                            snackbar.dismiss();
                                        }
                                    });
                        }
                    }));
        }
    }

    private UserResponse getUserObj() {
        getNavigator().getUser().setBinding_key(bindingKey);

        getNavigator().getUser().setEducationUniversity(getViewBinding().edSchoolUniversity.getText().toString());
        getNavigator().getUser().setEducationCountry(countryNameAdapter.getItem(getViewBinding().spinnerStudyCountry.getSelectedItemPosition()).getValue());
        getNavigator().getUser().setEducationStartDate(!start_date.isEmpty() ? start_date :
                getNavigator().getUser().getEducationStartDate());
        getNavigator().getUser().setEducationEndDate(!end_date.isEmpty() ? end_date :
                getNavigator().getUser().getEducationEndDate());
        getNavigator().getUser().setEducationSpecialty(getViewBinding().edSpecialization.getText().toString());
        return getNavigator().getUser();
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
