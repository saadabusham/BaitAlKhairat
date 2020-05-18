package com.saad.baitalkhairat.helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.R;

public class AdapterBinding {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.color.red);
        Glide.with(imageView.getContext()).applyDefaultRequestOptions(requestOptions).load(url).into(imageView);
    }

    @BindingAdapter("imageResources")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("viewBackground")
    public static void setViewBackground(View view, int resource) {
        view.setBackgroundResource(resource);
    }

    @BindingAdapter("textViewColor")
    public static void setTextViewColor(TextView textView, int resource) {
        textView.setTextColor(App.getInstance().getResources().getColor(resource));
    }
}
