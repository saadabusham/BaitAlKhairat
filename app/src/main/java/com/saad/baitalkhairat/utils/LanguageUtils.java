package com.saad.baitalkhairat.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.ui.splashscreen.SplashScreenActivity;
import com.yariksoffice.lingver.Lingver;

public class LanguageUtils {

    public static void setAppLanguage(Application application) {
        String lang = getLanguage(application);
        Lingver.init(application, "ar");

    }

    public static void updateLanguage(Activity activity, String lang) {
        Lingver.getInstance().setLocale(activity, lang);
        SessionManager.setLanguage(lang);
        activity.finishAffinity();
        activity.startActivity(SplashScreenActivity.newIntent(activity));
    }

    public static void setStyle(Context context, String lang) {
        if (lang.equals("ar")) {
            context.getTheme().applyStyle(R.style.AppThemeLight, true);
        } else {
            context.getTheme().applyStyle(R.style.AppThemeLight, true);
        }
    }

    public static String getLanguage(Context context) {
        SessionManager.init(context);
//        if (SessionManager.getisSetLanguage().isEmpty()) {
//            return Locale.getDefault().getDisplayLanguage().equals("العربية") ? "ar" : "en";
//        } else {
//            return SessionManager.getLanguage();
//        }
        return "ar";
    }


    public static int getStyle(Context context) {
        String lang = getLanguage(context);
        if (lang.equals("ar")) {
            return R.style.AppThemeArabic;
        } else {
            return R.style.AppThemeEnglish;
        }
    }

}
