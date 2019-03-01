package com.matias.journeytodependencyinjection.common.dependencyinjection;

import android.app.Activity;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentManager;

import com.matias.journeytodependencyinjection.common.ImageLoader;
import com.matias.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.matias.journeytodependencyinjection.common.mvp.BaseView;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsPresenterImpl;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsAdapter;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListActivity;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListPresenterImpl;

public class PresentationCompositionRoot {

    private final BaseView view;
    private final ApplicationComponent applicationComponent;
    private final FragmentManager fragmentManager;
    private final Activity activity;

    public PresentationCompositionRoot(BaseView view, ApplicationComponent applicationComponent,
                                       FragmentManager fragmentManager, Activity activity) {
        this.view = view;
        this.applicationComponent = applicationComponent;
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }

    private Activity getActivity() {
        return this.activity;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(this.fragmentManager);
    }

    private FetchQuestionsListInteractor getFetchQuestionsListInteractor() {
        return this.applicationComponent.getFetchQuestionsListInteractor();
    }

    private FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor() {
        return this.applicationComponent.getFetchQuestionDetailsInteractor();
    }

    @UiThread
    public QuestionsListPresenterImpl getQuestionsListPresenterImpl() {
        return new QuestionsListPresenterImpl(
                (QuestionsListActivity) view, getFetchQuestionsListInteractor());
    }

    @UiThread
    public QuestionDetailsPresenterImpl getQuestionDetailsPresenterImpl() {
        return new QuestionDetailsPresenterImpl(
                (QuestionDetailsActivity) view, getFetchQuestionDetailsInteractor());
    }

    public QuestionsAdapter getQuestionsAdapter() {
        return new QuestionsAdapter((QuestionsListActivity) view);
    }

    public ImageLoader getImageLoader() {
        return new ImageLoader(getActivity());
    }
}
