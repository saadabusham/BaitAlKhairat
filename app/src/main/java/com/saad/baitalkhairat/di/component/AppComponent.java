
package com.saad.baitalkhairat.di.component;

import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class})
public interface AppComponent {

    void inject(App app);

}
