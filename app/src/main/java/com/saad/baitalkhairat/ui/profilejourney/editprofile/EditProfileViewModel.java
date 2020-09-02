package com.saad.baitalkhairat.ui.profilejourney.editprofile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentEditProfileBinding;
import com.saad.baitalkhairat.enums.DialogTypes;
import com.saad.baitalkhairat.enums.PickImageTypes;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.account.ProfileResponse;
import com.saad.baitalkhairat.model.account.UserResponse;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.CustomObserverResponse;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.CustomUploadingDialog;
import com.saad.baitalkhairat.ui.dialog.OnLineDialog;
import com.saad.baitalkhairat.ui.dialog.PickImageFragmentDialog;
import com.saad.baitalkhairat.utils.PickImageUtility;
import com.saad.baitalkhairat.utils.ProgressRequestBody;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditProfileViewModel extends BaseViewModel<EditProfileNavigator, FragmentEditProfileBinding>
        implements ProgressRequestBody.UploadCallbacks {

    CustomUploadingDialog customUploadingDialog;

    ArrayList<ListItem> countryNameList = new ArrayList<>();
    ArrayAdapter<ListItem> countryNameAdapter;

    ArrayList<ListItem> maritalList = new ArrayList<>();
    ArrayAdapter<ListItem> maritalAdapter;

    ArrayList<ListItem> genderList = new ArrayList<>();
    ArrayAdapter<ListItem> genderAdapter;

    String bindingKey = "";

    String birth_date = "";

    public <V extends ViewDataBinding, N extends BaseNavigator> EditProfileViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (EditProfileNavigator) navigation, (FragmentEditProfileBinding) viewDataBinding);
    }

    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {

        getViewBinding().edDay.setText(String.valueOf(dayOfMonth));
        getViewBinding().edMonth.setText(String.valueOf(monthOfYear + 1));
        getViewBinding().edYear.setText(String.valueOf(year));
        birth_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        isValid();
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
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            isValid();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    protected void setUp() {
        bindingKey = GeneralFunction.generateUUID();
        getViewBinding().setData(getNavigator().getUser());
        customUploadingDialog = new CustomUploadingDialog(getMyContext());
        setUpSpinnerGender();
        setUpSpinnerCountry();
        setUpSpinnerCity();
        setUpSpinnerSocialStatus();
        setUpWatcher();
    }

    private void setUpWatcher() {
        getViewBinding().edUserName.addTextChangedListener(textWatcher);
        getViewBinding().edEmail.addTextChangedListener(textWatcher);

        getViewBinding().spinnerGender.setOnItemSelectedListener(onItemSelectedListener);
        getViewBinding().spinnerCountry.setOnItemSelectedListener(onItemSelectedListener);
        getViewBinding().spinnerSocialStatus.setOnItemSelectedListener(onItemSelectedListener);
        getViewBinding().spinnerCity.setOnItemSelectedListener(onItemSelectedListener);
    }

    private void setUpSpinnerGender() {
        genderList.add(new ListItem(getMyContext().getResources().getString(R.string.gender)));
        genderAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, genderList);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerGender.setAdapter(genderAdapter);
        getGenders();
    }

    private void getGenders() {
        getDataManager().getAppService().getGenders(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                genderAdapter.addAll(response.getList());
                genderAdapter.notifyDataSetChanged();
                getViewBinding().spinnerGender.setSelection(getNavigator().getUser().getMyGender(response.getList()));
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void setUpSpinnerCountry() {
        countryNameList.add(new ListItem(getMyContext().getResources().getString(R.string.country_original)));
        countryNameAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, countryNameList);
        countryNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCountry.setAdapter(countryNameAdapter);

        getCountryNames();
    }

    private void getCountryNames() {
        getDataManager().getAppService().getCountryName(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                countryNameAdapter.addAll(response.getList());
                countryNameAdapter.notifyDataSetChanged();
                getViewBinding().spinnerCountry.setSelection(getNavigator().getUser().getMyCountry(response.getList()));
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void setUpSpinnerCity() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.city));
        arrayList.add(getMyContext().getResources().getString(R.string.amman));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCity.setAdapter(adapter);
    }

    private void setUpSpinnerSocialStatus() {
        maritalList.add(new ListItem(getMyContext().getResources().getString(R.string.social_status)));
        maritalAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, maritalList);
        maritalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerSocialStatus.setAdapter(maritalAdapter);
        getMaritals();
    }

    private void getMaritals() {
        getDataManager().getAppService().getMaritals(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                maritalAdapter.addAll(response.getList());
                maritalAdapter.notifyDataSetChanged();
                getViewBinding().spinnerSocialStatus.setSelection(getNavigator().getUser().getMyMarital(response.getList()));
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
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

    public void updateClicked() {
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
                                            .navigate(R.id.action_editProfileFragment_to_nav_account);
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


    public void changePasswordClicked() {
//        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
//                .navigate(R.id.action_editProfileFragment_to_changePasswordFragment);
    }

    public void uploadProfilePicture(Uri uri) {
        customUploadingDialog.showProgress();
        getDataManager().getAuthService().getDataApi()
                .updateProfilePicture(GeneralFunction.getImageMultiPartWithProgress(uri.getPath(),
                        "profile_img", this),
                        bindingKey,
                        getNavigator().getUser().getEmail(),
                        getNavigator().getUser().getName(),
                        getUserObj().getBirthDate(),
                        getNavigator().getUser().getCountryOfResidence(),
                        getNavigator().getUser().getGender(),
                        getNavigator().getUser().getDescription(),
                        "PUT")
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<ProfileResponse>(getMyContext(), false,
                        new APICallBack<ProfileResponse>() {
                            @Override
                            public void onSuccess(ProfileResponse response) {
                                customUploadingDialog.setProgress(100);
                                new OnLineDialog(getMyContext()) {
                                    @Override
                                    public void onPositiveButtonClicked() {
                                        dismiss();
                                        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                                .navigate(R.id.action_editProfileFragment_to_nav_account);
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
                                customUploadingDialog.setProgress(100);
                            }
                        }));

    }

    public void updatePictureClick() {
        PickImageFragmentDialog pickImageFragmentDialog = new PickImageFragmentDialog.Builder().build();
        pickImageFragmentDialog.setMethodCallBack(new PickImageFragmentDialog.methodClick() {
            @Override
            public void onMethodBack(int type) {
                if (type == PickImageTypes.GALLERY.getIntValue()) {
                    PickImageUtility.selectImage(getBaseActivity());
                } else {
                    PickImageUtility.TakePictureIntent(getBaseActivity());
                }
            }
        });
        pickImageFragmentDialog.show(getBaseActivity().getSupportFragmentManager(), "picker");
    }

    @Override
    public void onProgressUpdate(int percentage) {
        customUploadingDialog.setProgress(percentage);
    }

    @Override
    public void onError() {
        customUploadingDialog.setProgress(100);
    }

    @Override
    public void onFinish() {
        customUploadingDialog.setProgress(100);
    }

    private UserResponse getUserObj() {
        UserResponse userResponse = new UserResponse();
        userResponse.setBinding_key(bindingKey);
        userResponse.setEmail(getViewBinding().edEmail.getText().toString());
        userResponse.setName(getViewBinding().edUserName.getText().toString());
        userResponse.setBirthDate(!birth_date.isEmpty() ? birth_date : getNavigator().getUser().getBirthDate());
        userResponse.setCountryOfResidence(getViewBinding().spinnerCountry.getSelectedItemPosition() != 0 ?
                countryNameAdapter.getItem(getViewBinding().spinnerCountry
                        .getSelectedItemPosition()).getValue() : getNavigator().getUser().getCountryOfResidence());
        userResponse.setGender(getViewBinding().spinnerGender.getSelectedItemPosition() != 0 ?
                Integer.parseInt(genderAdapter.getItem(getViewBinding()
                        .spinnerGender.getSelectedItemPosition()).getValue())
                : getNavigator().getUser().getGender());
        userResponse.setDescription(getViewBinding().edAboutMe.getText().toString());
        return userResponse;
    }

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

        if (getViewBinding().edDay.getText().toString().isEmpty()) {
            error = +1;
            getViewBinding().edDay.setError(getMyContext().getString(R.string.birthday_is_required));
        }

        if (getViewBinding().spinnerCountry.getSelectedItemPosition() == 0) {
            error = +1;
        }

//        if (getViewBinding().spinnerCity.getSelectedItemPosition() == 0) {
//            error = +1;
//        }

        if (getViewBinding().spinnerGender.getSelectedItemPosition() == 0) {
            error = +1;
        }

        if (error == 0)
            checkValidate(true);
        else
            checkValidate(false);
        return error == 0;
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

}
