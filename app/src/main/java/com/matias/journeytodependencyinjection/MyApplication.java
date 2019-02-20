package com.matias.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import com.matias.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;

public class MyApplication extends Application {

    private CompositionRoot compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        this.compositionRoot = new CompositionRoot();
    }

    @UiThread
    public CompositionRoot getCompositionRoot() {
        return this.compositionRoot;
    }
}