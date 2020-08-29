package com.saad.baitalkhairat.ui.donatejourney.donordetails;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentDonorsDetailsBinding;
import com.saad.baitalkhairat.enums.ProcessTypes;
import com.saad.baitalkhairat.model.donors.MyDonors;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

public class DonorsDetailsViewModel extends BaseViewModel<DonorsDetailsNavigator, FragmentDonorsDetailsBinding> {

    public <V extends ViewDataBinding, N extends BaseNavigator> DonorsDetailsViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (DonorsDetailsNavigator) navigation, (FragmentDonorsDetailsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        getViewBinding().setData(getDonorsObj());
    }

    private MyDonors getDonorsObj() {
        return getNavigator().getDonorsObj();
    }

    public int getProcessImage(int processType, boolean isImage) {
        ProcessTypes processTypesObj = ProcessTypes.fromInt(processType);
        switch (processTypesObj) {
            case ORDER_RECEIVED:
                return imageOrTextColor(isImage, ProcessTypes.ORDER_RECEIVED.getStatus());
            case UNDER_STUDYING:
                return imageOrTextColor(isImage, ProcessTypes.UNDER_STUDYING.getStatus());
            case IN_WAY:
                return imageOrTextColor(isImage, ProcessTypes.IN_WAY.getStatus());
            default:
                return imageOrTextColor(isImage, ProcessTypes.FINISHED.getStatus());
        }
    }

    private int imageOrTextColor(boolean isImage, int status) {
        if (isImage) {
            return getImage(status);
        } else {
            return getColor(status);
        }
    }

    private int getImage(int orderStatus) {
        if (getDonorsObj().getStatus() == ProcessTypes.FINISHED.getStatus()) {
            return R.drawable.ic_process_done;
        }
        if (getDonorsObj().getStatus() > orderStatus) {
            return R.drawable.ic_process_done;
        } else if (getDonorsObj().getStatus() == orderStatus) {
            return R.drawable.ic_process_start;
        } else {
            return R.drawable.ic_process_wait;
        }
    }

    public int getProcessColor(int processType) {
        ProcessTypes processTypesObj = ProcessTypes.fromInt(processType);
        switch (processTypesObj) {
            case UNDER_STUDYING:
                return getColor(ProcessTypes.UNDER_STUDYING.getStatus());
            case IN_WAY:
                return getColor(ProcessTypes.IN_WAY.getStatus());
            default:
                return getColor(ProcessTypes.FINISHED.getStatus());
        }
    }

    private int getColor(int orderStatus) {
        if (getDonorsObj().getStatus() >= orderStatus) {
            return R.color.green;
        } else {
            return R.color.process_gray;
        }
    }
}
