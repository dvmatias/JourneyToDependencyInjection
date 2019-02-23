package com.matias.journeytodependencyinjection.screens.questionlist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.common.mvp.BaseActivity;
import com.matias.journeytodependencyinjection.model.ui.Question;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

public class QuestionsListActivity extends BaseActivity implements QuestionsListContract.View {

    public QuestionsAdapter questionsAdapter;
    public QuestionsListPresenterImpl presenter;
    public DialogsManager dialogsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.layoutResource = R.layout.activity_main;
        super.onCreate(savedInstanceState);
        getInjector().inject(this);

        // init recycler view
        RecyclerView rvQuestions = findViewById(R.id.rv_questions);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        rvQuestions.setAdapter(questionsAdapter);
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