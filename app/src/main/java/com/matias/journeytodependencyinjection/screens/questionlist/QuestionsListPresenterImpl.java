package com.matias.journeytodependencyinjection.screens.questionlist;

import com.matias.journeytodependencyinjection.common.BasePresenterImpl;
import com.matias.journeytodependencyinjection.questions.Question;

import java.util.List;

public class QuestionsListPresenterImpl extends BasePresenterImpl<QuestionsListContract.View> implements
        FetchQuestionsListInteractor.Callback, QuestionsListContract.Presenter {

    private FetchQuestionsListInteractor interactor;

    QuestionsListPresenterImpl(QuestionsListActivity view,
                               FetchQuestionsListInteractor interactor) {
        bind(view);
        this.interactor = interactor;
    }

    @Override
    public void fetchQuestions(int pageSize) {
        if (isBound()) {
            interactor.fetch(this);
        }
    }

    @Override
    public void onResponse(List<Question> questionList) {
        if (isBound()) {
            this.view.showQuestionsList(questionList);
        }
    }

    @Override
    public void onFailure() {
        if (isBound()) {
            this.view.showServerErrorDialogFragment();
        }
    }
}