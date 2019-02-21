package com.matias.journeytodependencyinjection.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.matias.journeytodependencyinjection.MyApplication;
import com.matias.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Layout resource to be inflated.
     */
    protected int layoutResource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource);
    }

    protected CompositionRoot getCompositionRoot(){
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
