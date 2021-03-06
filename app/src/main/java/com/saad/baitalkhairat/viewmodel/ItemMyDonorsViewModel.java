
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.enums.ProcessTypes;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.donors.MyDonors;


public class ItemMyDonorsViewModel extends BaseObservable {

    private final Context mContext;
    private MyDonors myDonors;
    private int position;
    private RecyclerClick mRecyclerClick;

    public ItemMyDonorsViewModel(Context context, MyDonors myDonors, int position, RecyclerClick mRecyclerClick) {
        this.mContext = context;
        this.myDonors = myDonors;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public MyDonors getMyDonors() {
        return myDonors;
    }

    public void setMyDonors(MyDonors myDonors, int position) {
        this.myDonors = myDonors;
        this.position = position;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(myDonors, position);
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
        if (myDonors.getStatus() == ProcessTypes.FINISHED.getStatus()) {
            return R.drawable.ic_process_done;
        }
        if (myDonors.getStatus() > orderStatus) {
            return R.drawable.ic_process_done;
        } else if (myDonors.getStatus() == orderStatus) {
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
        if (myDonors.getStatus() >= orderStatus) {
            return R.color.green;
        } else {
            return R.color.process_gray;
        }
    }

}
