package com.matias.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListActivity;

import dagger.Component;

@PresentationScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = PresentationModule.class
)
public interface PresentationComponent {

    void inject (QuestionsListActivity questionsListActivity);
    void inject (QuestionDetailsActivity questionDetailsActivity);

}
