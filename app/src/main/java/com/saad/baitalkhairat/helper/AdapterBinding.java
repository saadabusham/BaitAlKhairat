package com.saad.baitalkhairat.helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.R;

public class AdapterBinding {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.progress_drawable_semi_small)
                .dontAnimate()
                .error(R.color.navigation_gray)
                .into(imageView);
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
