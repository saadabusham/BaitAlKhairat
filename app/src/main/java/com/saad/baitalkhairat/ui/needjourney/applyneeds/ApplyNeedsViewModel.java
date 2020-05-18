package com.saad.baitalkhairat.ui.needjourney.applyneeds;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentApplyNeedsBinding;
import com.saad.baitalkhairat.enums.PickImageTypes;
import com.saad.baitalkhairat.interfaces.RecycleDeleteClick;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.model.IdentificationDocument;
import com.saad.baitalkhairat.repository.DataManager;
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

    public <V extends ViewDataBinding, N extends BaseNavigator> ApplyNeedsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ApplyNeedsNavigator) navigation, (FragmentApplyNeedsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecyclerAmount();
        setUpRecyclerDocument();
        setUpSpinnerCaseType();
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
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getMyContext().getResources().getString(R.string.family));
        arrayList.add(getMyContext().getResources().getString(R.string.orphan));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMyContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getViewBinding().spinnerCaseType.setAdapter(adapter);
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
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_applyNeedsFragment_to_needAppliedFragment);
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
