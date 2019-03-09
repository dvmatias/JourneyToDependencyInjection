package com.matias.journeytodependencyinjection.screens.questionlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.common.mvp.BaseActivity;
import com.matias.journeytodependencyinjection.databinding.ActivityQuestionListBinding;
import com.matias.journeytodependencyinjection.model.ui.Question;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

import javax.inject.Inject;

public class QuestionsListActivity extends BaseActivity implements QuestionsListContract.View {

    //private ActivityQuestionListBinding binding;
    ActivityQuestionListBinding binding;

    @Inject QuestionsAdapter questionsAdapter;
    @Inject QuestionsListPresenterImpl presenter;
    @Inject DialogsManager dialogsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_question_list);

        getPresentationComponent().inject(this);

        // init recycler view
        binding.rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        binding.rvQuestions.setAdapter(questionsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.fetchQuestions(20);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbind();
    }

    @Override
    public void showQuestionsList(List<Question> questionList) {
        questionsAdapter.bindData(questionList);
    }

    @Override
    public void showServerErrorDialogFragment() {
        dialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), null);
    }

    @Override
    public void goToQuestionDetailActivity(String questionId) {
        QuestionDetailsActivity.start(QuestionsListActivity.this, questionId);
    }
}