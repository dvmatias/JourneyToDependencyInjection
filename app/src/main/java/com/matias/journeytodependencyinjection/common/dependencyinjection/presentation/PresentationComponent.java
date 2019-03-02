package com.matias.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.matias.journeytodependencyinjection.common.ImageLoader;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsPresenterImpl;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsAdapter;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListPresenterImpl;

import dagger.Component;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {

    DialogsManager getDialogsManager();
    FetchQuestionsListInteractor getFetchQuestionsListInteractor();
    FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor();
    QuestionsListPresenterImpl getQuestionsListPresenterImpl();
    QuestionDetailsPresenterImpl getQuestionDetailsPresenterImpl();
    QuestionsAdapter getQuestionsAdapter();
    ImageLoader getImageLoader();

}
