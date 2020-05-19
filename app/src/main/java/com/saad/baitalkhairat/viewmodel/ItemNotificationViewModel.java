
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.Notification;
import com.saad.baitalkhairat.utils.LanguageUtils;


public class ItemNotificationViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Notification notification;
    private int position;

    public ItemNotificationViewModel(Context context, Notification notification, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.notification = notification;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(notification, position);
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(context).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }

}
