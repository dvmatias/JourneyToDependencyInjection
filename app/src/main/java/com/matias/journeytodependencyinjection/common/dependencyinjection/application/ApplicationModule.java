package com.matias.journeytodependencyinjection.common.dependencyinjection.application;

import android.app.Application;

import dagger.Module;

@SuppressWarnings("ALL")
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }
}
