package com.matias.journeytodependencyinjection.screens.questiondetails;

import com.matias.journeytodependencyinjection.common.mvp.BasePresenter;
import com.matias.journeytodependencyinjection.common.mvp.BaseView;

/**
 * MVP Contract for {@link QuestionDetailsActivity} screen.
 */
public interface QuestionDetailsContract {

    interface View extends BaseView {
        void showOwnerAvatar(String imageUrl);
        void showOwnerName(String name);
        void showQuestionBody(String body);
        void showServerErrorDialogFragment();
    }

    interface Presenter extends BasePresenter<View> {
        void fetchQuestionDetails(String questionId);
    }
}