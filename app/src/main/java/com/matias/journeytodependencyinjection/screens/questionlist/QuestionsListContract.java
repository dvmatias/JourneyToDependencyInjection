package com.matias.journeytodependencyinjection.screens.questionlist;

import com.matias.journeytodependencyinjection.common.mvp.BasePresenter;
import com.matias.journeytodependencyinjection.common.mvp.BaseView;
import com.matias.journeytodependencyinjection.model.ui.Question;

import java.util.List;

/**
 * MVP Contract for {@link QuestionsListActivity} screen.
 */
public interface QuestionsListContract {

    /**
     * MVP - View interface.
     * Action callbacks.
     */
    interface View extends BaseView {
        void showQuestionsList(List<Question> questionList);
        void showServerErrorDialogFragment();
        void goToQuestionDetailActivity(String questionId);
    }

    /**
     * MVP - Presenter interface.
     * User actions.
     */
    interface Presenter extends BasePresenter<View> {
        void fetchQuestions(int pageSize);
    }
}