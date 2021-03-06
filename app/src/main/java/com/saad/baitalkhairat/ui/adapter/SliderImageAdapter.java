package com.saad.baitalkhairat.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saad.baitalkhairat.databinding.CellSliderImageBinding;
import com.saad.baitalkhairat.helper.GeneralFunction;
import com.saad.baitalkhairat.model.slider.Slider;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

public class SliderImageAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Slider> sliderArrayList;


    public SliderImageAdapter(Context context, ArrayList<Slider> sliderArrayList) {
        mContext = context;
        this.sliderArrayList = sliderArrayList;
    }

    @Override
    public int getCount() {
        return sliderArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        CellSliderImageBinding cellSliderImageBinding = CellSliderImageBinding
                .inflate(inflater, container, false);
        Slider slider = sliderArrayList.get(position);
        GeneralFunction.loadImage(mContext, slider.getImage().getPath(), cellSliderImageBinding.imgPhoto);
        container.addView(cellSliderImageBinding.getRoot(), 0);
        return cellSliderImageBinding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
