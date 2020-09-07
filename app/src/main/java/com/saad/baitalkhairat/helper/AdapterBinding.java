package com.saad.baitalkhairat.helper;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.model.auth.User;

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

    @BindingAdapter("imageUrlWithHeader")
    public static void setImageUrlWithHeader(ImageView imageView, String url) {
        String token = "";
        if (User.getInstance().getTokenResponse() != null) {
            token = "Bearer " + User.getInstance().getTokenResponse().getAccessToken();
        } else {
            token = "Bearer " + "";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", token)
                .build());

        Glide.with(imageView.getContext())
                .load(glideUrl)
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

    @BindingAdapter("cardViewBackgroundColor")
    public static void setCardViewBackground(CardView cardView, String color) {
        try {
            cardView.setCardBackgroundColor(Color.parseColor(color));
        } catch (Exception e) {

        }
    }
}
