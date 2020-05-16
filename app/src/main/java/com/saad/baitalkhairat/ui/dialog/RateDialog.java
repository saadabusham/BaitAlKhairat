package com.saad.baitalkhairat.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.DialogRateBinding;
import com.saad.baitalkhairat.model.Rate;

public class RateDialog extends Dialog {

    int rating = 0;
    DialogRateBinding dialogRateBinding;
    Dialog dialog;
    RateCallback rateCallback;

    public RateDialog(@NonNull Context context, RateCallback rateCallback) {
        super(context);
        this.rateCallback = rateCallback;
    }

    public void showRateDialog() {
        dialog = new Dialog(getContext());
        dialogRateBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_rate, null, false);
        dialog.setContentView(dialogRateBinding.getRoot());
        dialogRateBinding.setViewModel(this);
        dialogRateBinding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating1, boolean fromUser) {
                rating = (int) rating1;
            }
        });

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 10);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setBackgroundDrawable(inset);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    public void onRateClicked() {
        dialog.dismiss();
        rateCallback.callback(getRateObj());
    }

    public void onCanceledClick() {
        dialog.dismiss();
    }

    private Rate getRateObj() {
        Rate rate = new Rate();
        rate.setRating(rating);
        return rate;
    }

    public interface RateCallback {
        void callback(Rate rate);
    }

}
