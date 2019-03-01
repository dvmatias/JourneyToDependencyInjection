package com.matias.journeytodependencyinjection;

import android.app.Application;

import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationModule;
import com.matias.journeytodependencyinjection.common.dependencyinjection.application.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}