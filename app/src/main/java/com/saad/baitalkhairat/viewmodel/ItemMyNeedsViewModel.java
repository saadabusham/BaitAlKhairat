
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.enums.ProcessStatus;
import com.saad.baitalkhairat.enums.ProcessTypes;
import com.saad.baitalkhairat.model.MyNeeds;


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

    public void setMyNeeds(MyNeeds myNeeds) {
        this.myNeeds = myNeeds;
        notifyChange();
    }

    public void onItemClick(View view) {

    }

    public int getProcessImage(int processType) {
        ProcessTypes processTypesObj = ProcessTypes.fromInt(processType);
        switch (processTypesObj) {
            case ORDER_RECEIVED:

                break;

            case UNDER_STUDYING:

                break;

            case IN_WAY:

                break;

            case FINISHED:

                break;
        }
        ProcessStatus processStatus = ProcessStatus.fromInt(myNeeds.getProcess());
        switch (processStatus) {
            case WAITING:
                return R.drawable.ic_process_wait;
            case IN_PROCESS:
                return R.drawable.ic_process_start;
            default:
                return R.drawable.ic_process_done;
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
        ProcessStatus processStatus = ProcessStatus.fromInt(myNeeds.getProcess());
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
