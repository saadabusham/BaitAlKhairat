
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.news.News;
import com.saad.baitalkhairat.utils.LanguageUtils;


public class ItemNewViewModel extends BaseObservable {

    private final Context context;
    RecyclerClick mRecyclerClick;
    private News news;
    private int position;

    public ItemNewViewModel(Context context, News news, int position, RecyclerClick mRecyclerClick) {
        this.context = context;
        this.news = news;
        this.position = position;
        this.mRecyclerClick = mRecyclerClick;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
        notifyChange();
    }

    public void onItemClick(View view) {
        mRecyclerClick.onClick(news, position);
    }

    public int getGravity() {
        return LanguageUtils.getLanguage(context).equals("ar")
                ? Gravity.RIGHT : Gravity.LEFT;
    }

}
