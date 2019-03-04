package com.matias.journeytodependencyinjection.common.dependencyinjection.application;

import com.matias.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.matias.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetworkingModule.class,
                InteractorModule.class
        }
)
public interface ApplicationComponent {

    PresentationComponent newPresentationComponent(PresentationModule presentationModule);

}