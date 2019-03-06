package com.matias.journeytodependencyinjection.common.mvp;

import android.support.annotation.UiThread;
import android.support.v4.app.DialogFragment;

import com.matias.journeytodependencyinjection.MyApplication;
import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.matias.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.matias.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

public abstract class BaseDialog extends DialogFragment implements BaseView {

    private boolean isInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (isInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        isInjectorUsed = true;
        return getApplicationComponent().newPresentationComponent(
                new PresentationModule(this,
                        getActivity().getSupportFragmentManager(),
                        getActivity()));
    }

    private ApplicationComponent getApplicationComponent(){
        return ((MyApplication) getActivity().getApplication()).getApplicationComponent();
    }
}