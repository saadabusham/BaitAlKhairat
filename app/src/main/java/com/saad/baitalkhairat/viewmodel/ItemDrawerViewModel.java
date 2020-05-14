
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.enums.DrawerWithIconTypes;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.MenuItem;

public class ItemDrawerViewModel extends BaseObservable {

    private final Context context;
    RecyclerClickNoData mRecyclerClick;
    private MenuItem menuItem;
    private int position;

    public ItemDrawerViewModel(Context context, MenuItem menuItem, int position, RecyclerClickNoData mRecyclerClick) {
        this.context = context;
        this.menuItem = menuItem;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        notifyChange();
    }

    public int isWithIcon() {
        return menuItem.isWithIcon() == DrawerWithIconTypes.WITH_ICON.getMode() ?
                View.VISIBLE : View.GONE;
    }

    public int isHideTitle() {
        return menuItem.isWithIcon() == DrawerWithIconTypes.HIDE_ITEM.getMode() ?
                View.GONE : View.VISIBLE;
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(position);
    }
}
