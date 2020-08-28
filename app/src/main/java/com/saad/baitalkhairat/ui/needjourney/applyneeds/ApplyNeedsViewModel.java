package com.saad.baitalkhairat.ui.needjourney.applyneeds;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentApplyNeedsBinding;
import com.saad.baitalkhairat.enums.PickImageTypes;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.model.IdentificationDocument;
import com.saad.baitalkhairat.model.ListItem;
import com.saad.baitalkhairat.model.country.countrycode.CountryCodeResponse;
import com.saad.baitalkhairat.model.errormodel.AddNeedError;
import com.saad.baitalkhairat.model.needs.AddNeed;
import com.saad.baitalkhairat.model.needs.AddNeedDocResponse;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBack;
import com.saad.baitalkhairat.repository.network.ApiCallHandler.APICallBackNew;
import com.saad.baitalkhairat.ui.adapter.AmountAdapter;
import com.saad.baitalkhairat.ui.adapter.DocumentAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.PickImageFragmentDialog;
import com.saad.baitalkhairat.utils.PickImageUtility;

import java.util.ArrayList;

public class ApplyNeedsViewModel extends BaseViewModel<ApplyNeedsNavigator, FragmentApplyNeedsBinding>
        implements RecyclerClick<Amount>, RecycleDeleteClick<IdentificationDocument> {

    AmountAdapter amountAdapter;
    DocumentAdapter documentAdapter;

    ArrayList<ListItem> countryNameList = new ArrayList<>();
    ArrayAdapter<ListItem> countryNameAdapter;

    ArrayList<ListItem> caseList = new ArrayList<>();
    ArrayAdapter<ListItem> caseAdapter;

    ArrayList<ListItem> degreeList = new ArrayList<>();
    ArrayAdapter<ListItem> degreeAdapter;

    String bindingKey = "";

    int responseCount = 0;

    public <V extends ViewDataBinding, N extends BaseNavigator> ApplyNeedsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ApplyNeedsNavigator) navigation, (FragmentApplyNeedsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpSpinnerCountry();
        setUpRecyclerAmount();
        setUpRecyclerDocument();
        setUpSpinnerCaseType();
        setUpSpinnerDegree();
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
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void setUpRecyclerAmount() {

        getViewBinding().recyclerViewAmounts.recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(),
                LinearLayoutManager.HORIZONTAL, false));
        getViewBinding().recyclerViewAmounts.recyclerView.setItemAnimator(new DefaultItemAnimator());
        amountAdapter = new AmountAdapter(getMyContext(), this);
        getViewBinding().recyclerViewAmounts.recyclerView.setAdapter(amountAdapter);
        getLocalData();
    }

    private void setUpRecyclerDocument() {
        getViewBinding().recyclerViewDocuments.recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(),
                LinearLayoutManager.HORIZONTAL, false));
        getViewBinding().recyclerViewDocuments.recyclerView.setItemAnimator(new DefaultItemAnimator());
        documentAdapter = new DocumentAdapter(getMyContext(), this);
        getViewBinding().recyclerViewDocuments.recyclerView.setAdapter(documentAdapter);
    }

    private void setUpSpinnerCaseType() {
        caseAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, caseList);
        caseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCaseType.setAdapter(caseAdapter);

        getCaseType();
    }

    private void getCaseType() {
        getDataManager().getAppService().getCaseType(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                caseAdapter.addAll(response.getList());
                caseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void setUpSpinnerDegree() {
        degreeAdapter = new ArrayAdapter<ListItem>(getMyContext(), android.R.layout.simple_spinner_item, degreeList);
        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerDegree.setAdapter(degreeAdapter);
        getDegree();
    }

    private void getDegree() {
        getDataManager().getAppService().getDegree(getMyContext(), true, new APICallBack<CountryCodeResponse>() {
            @Override
            public void onSuccess(CountryCodeResponse response) {
                degreeAdapter.addAll(response.getList());
                degreeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error, int errorCode) {
                showErrorSnackBar(error);
            }
        });
    }

    private void getLocalData() {
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.getItem(0).setSelected(true);
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));
        amountAdapter.addItem(new Amount("100", getMyContext().getResources().getString(R.string.jod)));

    }

    public void onUploadDocClick() {
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

    public void onApplyNeedClick() {

        if (isValid()) {
            getDataManager().getNeedsService().addNeed(getMyContext(), true, getNeedObject(), new APICallBackNew<String>() {
                @Override
                public void onSuccess(String response) {
                    if (documentAdapter.getItemCount() == 0) {
                        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.action_applyNeedsFragment_to_needAppliedFragment);
                    } else {
                        uploadDocuments();
                    }
                }

                @Override
                public void onError(String error, int errorCode) {
                    AddNeedError registerError = new Gson().fromJson(error, AddNeedError.class);
                    if (registerError.getTitle() != null) {
                        getViewBinding().edCaseTitle.setError(registerError.getTitle().toString());
                    }
                    if (registerError.getDescription() != null) {
                        getViewBinding().edCaseDetails.setError(registerError.getDescription().toString());
                    }
                    if (registerError.getAmount() != null) {
                        getViewBinding().edAmount.setError(registerError.getDescription().toString());
                    }

                    showToast(registerError.toString());
                }

                @Override
                public void onNetworkError(String error, int errorCode) {
                    showErrorSnackBar(error);
                }
            });
        }
    }

    private void uploadDocuments() {

        for (int i = 0; i < documentAdapter.getItemCount(); i++) {
            getDataManager().getNeedsService().addNeedDocs(getMyContext(), false,
                    GeneralFunction.getImageMultipart(documentAdapter.getItem(i).getImage(), "attachment"),
                    bindingKey, new APICallBack<AddNeedDocResponse>() {
                        @Override
                        public void onSuccess(AddNeedDocResponse response) {
                            responseCount++;
                            if (responseCount == documentAdapter.getItemCount()) {
                                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                        .navigate(R.id.action_applyNeedsFragment_to_needAppliedFragment);
                            }
                        }

                        @Override
                        public void onError(String error, int errorCode) {
                            responseCount++;
                            if (responseCount == documentAdapter.getItemCount()) {
                                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                                        .navigate(R.id.action_applyNeedsFragment_to_needAppliedFragment);
                            }
                        }
                    });
        }
    }

    private AddNeed getNeedObject() {
        bindingKey = GeneralFunction.generateUUID();
        return new AddNeed(getViewBinding().edCaseTitle.getText().toString(),
                getViewBinding().edCaseDetails.getText().toString(),
                !getViewBinding().edAmount.getText().toString().isEmpty() ?
                        Double.valueOf(getViewBinding().edAmount.getText().toString()) :
                        Double.valueOf(amountAdapter.getSelectedItem().getAmount()),
                Integer.valueOf(caseAdapter.getItem(getViewBinding().spinnerCaseType.getSelectedItemPosition()).getValue()),
                countryNameAdapter.getItem(getViewBinding().spinnerCountry.getSelectedItemPosition()).getValue(),
                Integer.valueOf(degreeAdapter.getItem(getViewBinding().spinnerDegree.getSelectedItemPosition()).getValue()),
                bindingKey);
    }

    private boolean isValid() {
        int error = 0;
        if (getViewBinding().spinnerCountry.getSelectedItemPosition() == 0) {
            error++;
        }
        if (caseAdapter.getCount() == 0) {
            error++;
        }
        if (degreeAdapter.getCount() == 0) {
            error++;
        }
        if (getViewBinding().edCaseTitle.getText().toString().isEmpty()) {
            getViewBinding().edCaseTitle.setError(getMyContext().getResources().getString(R.string.this_fieled_is_required));
            error++;
        }
        if (getViewBinding().edCaseDetails.getText().toString().isEmpty()) {
            getViewBinding().edCaseDetails.setError(getMyContext().getResources().getString(R.string.this_fieled_is_required));
            error++;
        }

        if (amountAdapter.getItemCount() == 0 && getViewBinding().edAmount.getText().toString().isEmpty()) {
            getViewBinding().edAmount.setError(getMyContext().getResources().getString(R.string.this_fieled_is_required));
            error++;
        }
        return error == 0;
    }

    @Override
    public void onClick(Amount amount, int position) {

    }

    @Override
    public void onClick(boolean isDelete, IdentificationDocument object, int position) {
        if (isDelete) {
            documentAdapter.remove(position);
            documentAdapter.notifyDataSetChanged();
        }
    }

    public void addImage(String imagePath) {
        documentAdapter.addItem(new IdentificationDocument(imagePath));
        getViewBinding().recyclerViewDocuments.recyclerView.scrollToPosition(documentAdapter.getItemCount() - 1);
    }
}
