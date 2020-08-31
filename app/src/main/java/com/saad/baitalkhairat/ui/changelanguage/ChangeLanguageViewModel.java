package com.saad.baitalkhairat.ui.changelanguage;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.FragmentChangeLanguageBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.LanguageUtils;

public class ChangeLanguageViewModel extends BaseViewModel<ChangeLanguageNavigator, FragmentChangeLanguageBinding> {


    public <V extends ViewDataBinding, N extends BaseNavigator> ChangeLanguageViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (ChangeLanguageNavigator) navigation, (FragmentChangeLanguageBinding) viewDataBinding);

    }


    public void onArClick() {
        getViewBinding().checkAr.setChecked(true);
        getViewBinding().checkEn.setChecked(false);
    }

    public void onEnClick() {
        getViewBinding().checkAr.setChecked(false);
        getViewBinding().checkEn.setChecked(true);
    }

    public void saveClicked() {
        if (getViewBinding().checkAr.isChecked()) {
            LanguageUtils.updateLanguage(getBaseActivity(), "ar");
            return;
        }
        LanguageUtils.updateLanguage(getBaseActivity(), "en");
    }

    @Override
    protected void setUp() {
        String lang = LanguageUtils.getLanguage(getMyContext());
        if (lang.equals("ar")) {
            getViewBinding().checkAr.setChecked(true);
        } else {
            getViewBinding().checkEn.setChecked(true);
        }
    }
}
