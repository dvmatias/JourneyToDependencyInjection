package com.matias.journeytodependencyinjection.common.dependencyinjection.presentation;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

import com.matias.journeytodependencyinjection.common.ImageLoader;
import com.matias.journeytodependencyinjection.common.mvp.BaseView;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsPresenterImpl;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsAdapter;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListActivity;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final BaseView view;
    private final FragmentManager fragmentManager;
    private final Activity activity;

    public PresentationModule(BaseView view, FragmentManager fragmentManager, Activity activity) {
        this.view = view;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }

    @Provides
    Activity getActivity() {
        return this.activity;
    }

    @Provides
    DialogsManager getDialogsManager() {
        return new DialogsManager(this.fragmentManager);
    }

    @Provides
    QuestionsListPresenterImpl getQuestionsListPresenterImpl(FetchQuestionsListInteractor fetchQuestionsListInteractor) {
        return new QuestionsListPresenterImpl(
                (QuestionsListActivity) view, fetchQuestionsListInteractor);
    }
    
    @Provides
    QuestionDetailsPresenterImpl getQuestionDetailsPresenterImpl(FetchQuestionDetailsInteractor fetchQuestionDetailsInteractor) {
        return new QuestionDetailsPresenterImpl(
                (QuestionDetailsActivity) view, fetchQuestionDetailsInteractor);
    }

    @Provides
    QuestionsAdapter getQuestionsAdapter() {
        return new QuestionsAdapter((QuestionsListActivity) view);
    }
}
