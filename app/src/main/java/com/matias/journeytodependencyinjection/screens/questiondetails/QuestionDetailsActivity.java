package com.matias.journeytodependencyinjection.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.matias.journeytodependencyinjection.MyApplication;
import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.common.BaseActivity;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;

public class QuestionDetailsActivity extends BaseActivity implements QuestionDetailsContract.View {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private QuestionDetailsPresenterImpl presenter;

    private TextView tvQuestionDetail;

    private String questionId;

    private DialogsManager dialogsManager;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.layoutResource = R.layout.activity_question_details;
        super.onCreate(savedInstanceState);

        tvQuestionDetail = findViewById(R.id.tv_question_detail);

        if (getIntent().getExtras() != null) {
            questionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
        }

        dialogsManager = new DialogsManager(getSupportFragmentManager());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter == null) {
            StackoverflowApi stackoverflowApi =
                    ((MyApplication) getApplication()).getStackoverflowApi();
            presenter = new QuestionDetailsPresenterImpl(
                    this, new FetchQuestionDetailsInteractor(stackoverflowApi));
        }
        presenter.fetchQuestionDetails(questionId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbind();
    }

    @Override
    public void showQuestionDetail(String questionDetails) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvQuestionDetail.setText(Html.fromHtml(questionDetails, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvQuestionDetail.setText(Html.fromHtml(questionDetails));
        }
    }

    @Override
    public void showServerErrorDialogFragment() {
        dialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), null);
    }
}