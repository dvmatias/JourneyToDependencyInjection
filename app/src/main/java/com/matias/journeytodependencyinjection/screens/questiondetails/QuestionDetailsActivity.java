package com.matias.journeytodependencyinjection.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.common.ImageLoader;
import com.matias.journeytodependencyinjection.common.mvp.BaseActivity;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;

import javax.inject.Inject;

public class QuestionDetailsActivity extends BaseActivity implements QuestionDetailsContract.View {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private ImageView ivUserAvatar;
    private TextView tvUserName;
    private TextView tvQuestionDetail;
    private String questionId;

    @Inject QuestionDetailsPresenterImpl presenter;
    @Inject DialogsManager dialogsManager;
    @Inject ImageLoader imageLoader;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.layoutResource = R.layout.activity_question_details;
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);

        ivUserAvatar = findViewById(R.id.iv_user_avatar);
        tvUserName = findViewById(R.id.tv_user_name);
        tvQuestionDetail = findViewById(R.id.tv_question_detail);

        if (getIntent().getExtras() != null) {
            questionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.fetchQuestionDetails(questionId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbind();
    }

    @Override
    public void showOwnerAvatar(String imageUrl) {
        imageLoader.loadImage(imageUrl, ivUserAvatar);
    }

    @Override
    public void showOwnerName(String name) {
        tvUserName.setText(name);
    }

    @Override
    public void showQuestionBody(String body) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvQuestionDetail.setText(Html.fromHtml(body, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvQuestionDetail.setText(Html.fromHtml(body));
        }
    }

    @Override
    public void showServerErrorDialogFragment() {
        dialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), null);
    }
}