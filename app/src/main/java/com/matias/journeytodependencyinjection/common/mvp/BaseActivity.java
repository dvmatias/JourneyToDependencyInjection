package com.matias.journeytodependencyinjection.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.matias.journeytodependencyinjection.MyApplication;
import com.matias.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;
import com.matias.journeytodependencyinjection.common.dependencyinjection.Injector;
import com.matias.journeytodependencyinjection.common.dependencyinjection.PresentationCompositionRoot;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Layout resource to be inflated.
     */
    protected int layoutResource;

    private PresentationCompositionRoot presentationCompositionRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource);
    }

    @UiThread
    protected Injector getInjector() {
        return new Injector(getCompositionRoot());
    }

    @UiThread
    protected PresentationCompositionRoot getCompositionRoot() {
        if (presentationCompositionRoot == null) {
            presentationCompositionRoot = new PresentationCompositionRoot(
                    this,
                    getAppCompositionRoot(),
                    getSupportFragmentManager(),
                    this
            );
        }

        return presentationCompositionRoot;
    }

    private CompositionRoot getAppCompositionRoot(){
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
