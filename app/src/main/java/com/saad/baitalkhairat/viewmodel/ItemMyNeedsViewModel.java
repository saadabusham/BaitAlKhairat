
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.enums.ProcessStatus;
import com.saad.baitalkhairat.enums.ProcessTypes;
import com.saad.baitalkhairat.model.needs.MyNeeds;


public class ItemMyNeedsViewModel extends BaseObservable {

    private final Context mContext;
    private MyNeeds myNeeds;
    private int position;

    public ItemMyNeedsViewModel(Context context, MyNeeds myNeeds, int position) {
        this.mContext = context;
        this.myNeeds = myNeeds;
        this.position = position;
    }

    public MyNeeds getMyNeeds() {
        return myNeeds;
    }

    public void setMyNeeds(MyNeeds myNeeds, int position) {
        this.myNeeds = myNeeds;
        this.position = position;
        notifyChange();
    }

    public void onItemClick(View view) {

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
            return getTextColor(status);
        }
    }

    private int getTextColor(int status) {
        if (myNeeds.getStatus() >= status) {
            return R.color.green;
        } else {
            return R.color.process_gray;
        }
    }

    public int getProcessTextColor(int processType) {
        ProcessTypes processTypesObj = ProcessTypes.fromInt(processType);
        switch (processTypesObj) {
            case ORDER_RECEIVED:
                return getImage(ProcessTypes.ORDER_RECEIVED.getStatus());
            case UNDER_STUDYING:
                return getImage(ProcessTypes.UNDER_STUDYING.getStatus());
            case IN_WAY:
                return getImage(ProcessTypes.IN_WAY.getStatus());
            default:
                return getImage(ProcessTypes.FINISHED.getStatus());
        }
    }

    private int getImage(int orderStatus) {
        if (myNeeds.getStatus() == ProcessTypes.FINISHED.getStatus()) {
            return R.drawable.ic_process_done;
        }
        if (myNeeds.getStatus() > orderStatus) {
            return R.drawable.ic_process_done;
        } else if (myNeeds.getStatus() == orderStatus) {
            return R.drawable.ic_process_start;
        } else {
            return R.drawable.ic_process_wait;
        }
    }

    public int getProcessColor(int processType) {
        ProcessTypes processTypesObj = ProcessTypes.fromInt(processType);
        switch (processTypesObj) {
            case UNDER_STUDYING:
                return getLineColor(ProcessTypes.UNDER_STUDYING.getStatus());
            case IN_WAY:
                return getLineColor(ProcessTypes.IN_WAY.getStatus());
            default:
                return getLineColor(ProcessTypes.FINISHED.getStatus());
        }
    }

    private int getLineColor(int orderStatus) {
        if (myNeeds.getStatus() >= orderStatus) {
            return R.color.green;
        } else {
            return R.color.process_gray;
        }
    }

    //    private int returnImage(int processTypesObj){
//        switch (processTypesObj){
//            case ORDER_RECEIVED:
//
//                break;
//
//            case UNDER_STUDYING:
//
//                break;
//
//            case IN_WAY:
//
//                break;
//
//            case FINISHED:
//
//                break;
//        }
//    }
    public int getProcessTextColor() {
        ProcessStatus processStatus = ProcessStatus.fromInt(myNeeds.getStatus());
        switch (processStatus) {
            case WAITING:
                return R.color.green;
            case IN_PROCESS:
                return R.color.green;
            default:
                return R.color.green;
        }
    }

}
