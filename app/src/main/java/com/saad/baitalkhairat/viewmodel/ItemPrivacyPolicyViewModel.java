
package com.saad.baitalkhairat.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.saad.baitalkhairat.model.app.privacypolicy.PrivacyPolicy;

public class ItemPrivacyPolicyViewModel extends BaseObservable {

    private final Context context;
    private PrivacyPolicy privacyPolicy;
    private int position;

    public ItemPrivacyPolicyViewModel(Context context, PrivacyPolicy privacyPolicy, int position) {
        this.context = context;
        this.privacyPolicy = privacyPolicy;
        this.position = position;
    }

    public PrivacyPolicy getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(PrivacyPolicy privacyPolicy, int position) {
        this.privacyPolicy = privacyPolicy;
        this.position = position;
        notifyChange();
    }
}
