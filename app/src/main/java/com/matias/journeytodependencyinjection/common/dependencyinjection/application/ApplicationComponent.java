package com.matias.journeytodependencyinjection.common.dependencyinjection.application;

import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = ApplicationModule.class
)
public interface ApplicationComponent {
    public FetchQuestionsListInteractor getFetchQuestionsListInteractor();
    public FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor();
}