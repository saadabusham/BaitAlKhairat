package com.saad.baitalkhairat.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.DialogUploadProgressBinding;

public class CustomUploadingDialog extends Dialog {

    private DialogUploadProgressBinding dialogUploadProgressBinding;

    public CustomUploadingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogUploadProgressBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_upload_progress, null, false);
        setContentView(dialogUploadProgressBinding.getRoot());
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    public void showProgress() {
        dialogUploadProgressBinding.donutProgress.setProgress(0);
        show();
    }

    public void setProgress(int percent) {
        dialogUploadProgressBinding.donutProgress.setProgress(percent);
        if (percent == 100) {
            dismiss();
        }
    }


}