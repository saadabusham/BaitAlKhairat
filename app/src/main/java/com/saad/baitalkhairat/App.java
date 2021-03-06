package com.saad.baitalkhairat;

import android.app.Application;

import com.saad.baitalkhairat.utils.LanguageUtils;

public class App extends Application {

    private static App sInstance;

    public static App getInstance() {
        return sInstance;
    }

    /**
     * Part of @Dagger Framework injection to use activity
     */
    //    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        DaggerAppComponent.builder()
//                .application(this)
//                .build()
//                .inject(this);
        LanguageUtils.setAppLanguage(this);
    }

}
