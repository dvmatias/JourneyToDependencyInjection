package com.matias.journeytodependencyinjection.screens.questiondetails;

import com.matias.journeytodependencyinjection.common.BasePresenterImpl;

public class QuestionDetailsPresenterImpl extends BasePresenterImpl<QuestionDetailsContract.View>
        implements QuestionDetailsContract.Presenter, FetchQuestionDetailsInteractor.Callback {

    private FetchQuestionDetailsInteractor interactor;

    QuestionDetailsPresenterImpl (QuestionDetailsActivity view,
                                  FetchQuestionDetailsInteractor interactor) {
        bind(view);
        this.interactor = new FetchQuestionDetailsInteractor();
    }

    @Override
    public void fetchQuestionDetails(String questionId) {
        if (isBound()) {
            interactor.fetch(this, questionId);
        }
    }

    @Override
    public void onResponse(String questionDetails) {
        if (isBound()) {
            view.showQuestionDetail(questionDetails);
        }
    }

    @Override
    public void onFailure() {
        if(isBound()) {
            view.showServerErrorDialogFragment();
        }
    }
}