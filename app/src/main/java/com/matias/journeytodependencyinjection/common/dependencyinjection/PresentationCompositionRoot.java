package com.matias.journeytodependencyinjection.common.dependencyinjection;

import android.support.annotation.UiThread;
import android.support.v4.app.FragmentManager;

import com.matias.journeytodependencyinjection.common.mvp.BaseActivity;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsPresenterImpl;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListActivity;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListPresenterImpl;

public class PresentationCompositionRoot {

    private final BaseActivity view;
    private final CompositionRoot compositionRoot;
    private final FragmentManager fragmentManager;

    public PresentationCompositionRoot(BaseActivity view, CompositionRoot compositionRoot,
                                       FragmentManager fragmentManager) {
        this.view = view;
        this.compositionRoot = compositionRoot;
        this.fragmentManager = fragmentManager;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(this.fragmentManager);
    }

    private FetchQuestionsListInteractor getFetchQuestionsListInteractor() {
        return this.compositionRoot.getFetchQuestionsListInteractor();
    }

    private FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor() {
        return this.compositionRoot.getFetchQuestionDetailsInteractor();
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
}
