package com.matias.journeytodependencyinjection.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.matias.journeytodependencyinjection.MyApplication;
import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.matias.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.matias.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private boolean isInjectorUsed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (isInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        isInjectorUsed = true;
        return getApplicationComponent().newPresentationComponent(
                new PresentationModule(this, getSupportFragmentManager(), this));
    }

    private ApplicationComponent getApplicationComponent(){
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}