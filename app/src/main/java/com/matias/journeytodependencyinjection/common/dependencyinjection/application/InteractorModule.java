package com.matias.journeytodependencyinjection.common.dependencyinjection.application;

import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    FetchQuestionsListInteractor getFetchQuestionsListInteractor(
            StackoverflowApi stackoverflowApi) {
        return new FetchQuestionsListInteractor(stackoverflowApi);
    }

    @Provides
    FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor(
            StackoverflowApi stackoverflowApi) {
        return  new FetchQuestionDetailsInteractor(stackoverflowApi);
    }
}