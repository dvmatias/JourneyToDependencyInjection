package com.matias.journeytodependencyinjection.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.matias.journeytodependencyinjection.MyApplication;
import com.matias.journeytodependencyinjection.common.dependencyinjection.Injector;
import com.matias.journeytodependencyinjection.common.dependencyinjection.PresentationCompositionRoot;
import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Layout resource to be inflated.
     */
    protected int layoutResource;

    private boolean isInjectorUsed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource);
    }

    @UiThread
    protected Injector getInjector() {
        if (isInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        isInjectorUsed = true;
        return new Injector(getCompositionRoot());
    }

    @UiThread
    private PresentationCompositionRoot getCompositionRoot() {
        return new PresentationCompositionRoot(
                    this,
                    getApplicationComponent(),
                    getSupportFragmentManager(),
                    this
            );
    }

    private ApplicationComponent getApplicationComponent(){
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
