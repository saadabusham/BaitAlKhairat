
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.model.app.termsofuse.TermsOfUse;

public class ItemTermsOfUseViewModel extends BaseObservable {

    private final Context context;
    private TermsOfUse termsOfUse;
    private int position;

    public ItemTermsOfUseViewModel(Context context, TermsOfUse termsOfUse, int position) {
        this.context = context;
        this.termsOfUse = termsOfUse;
        this.position = position;
    }

    public TermsOfUse getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(TermsOfUse termsOfUse, int position) {
        this.termsOfUse = termsOfUse;
        this.position = position;
        notifyChange();
    }
}
