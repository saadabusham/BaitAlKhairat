
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.category.Category;


public class ItemCategoryViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private Category category;
    private int position;

    public ItemCategoryViewModel(Context context, Category category, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.category = category;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(category, position);
    }
}
