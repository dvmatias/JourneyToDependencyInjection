package com.matias.journeytodependencyinjection.screens.questiondetails;

import com.matias.journeytodependencyinjection.common.mvp.BasePresenterImpl;
import com.matias.journeytodependencyinjection.model.ui.QuestionDetails;

public class QuestionDetailsPresenterImpl extends BasePresenterImpl<QuestionDetailsContract.View>
        implements QuestionDetailsContract.Presenter, FetchQuestionDetailsInteractor.Callback {

    private FetchQuestionDetailsInteractor interactor;

    public QuestionDetailsPresenterImpl(QuestionDetailsContract.View view,
                                        FetchQuestionDetailsInteractor interactor) {
        bind(view);
        this.interactor = interactor;
    }

    @Override
    public void fetchQuestionDetails(String questionId) {
        if (isBound()) {
            interactor.fetch(this, questionId);
        }
    }

    @Override
    public void onResponse(QuestionDetails questionDetails) {
        if (isBound()) {
            view.showOwnerAvatar(questionDetails.getOwner().getImageUrl());
            view.showOwnerName(questionDetails.getOwner().getName());
            view.showQuestionBody(questionDetails.getBody());
        }
    }

    @Override
    public void onFailure() {
        if(isBound()) {
            view.showServerErrorDialogFragment();
        }
    }
}