package com.matias.journeytodependencyinjection.screens.questiondetails;

import com.matias.journeytodependencyinjection.common.mvp.BasePresenter;
import com.matias.journeytodependencyinjection.common.mvp.BaseView;
import com.matias.journeytodependencyinjection.model.ui.QuestionDetails;

/**
 * MVP Contract for {@link QuestionDetailsActivity} screen.
 */
public interface QuestionDetailsContract {

    interface View extends BaseView {

        void showOwnerAvatar(String imageUrl);

        void showServerErrorDialogFragment();

        void bindQuestionDetails(QuestionDetails questionDetails);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchQuestionDetails(String questionId);
    }
}