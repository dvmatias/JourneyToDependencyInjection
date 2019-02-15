package com.matias.journeytodependencyinjection.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.matias.journeytodependencyinjection.Constants;
import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.networking.SingleQuestionResponseSchema;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionDetailsActivity extends AppCompatActivity
        implements Callback<SingleQuestionResponseSchema> {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private TextView tvQuestionDetail;

    private StackoverflowApi stackoverflowApi;

    private Call<SingleQuestionResponseSchema> call;

    private String questionId;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        tvQuestionDetail = findViewById(R.id.tv_question_detail);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackoverflowApi = retrofit.create(StackoverflowApi.class);

        //noinspection ConstantConditions
        questionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        call = stackoverflowApi.questionDetails(questionId);
        call.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public void onResponse(@NonNull Call<SingleQuestionResponseSchema> call,
                           @NonNull Response<SingleQuestionResponseSchema> response) {
        SingleQuestionResponseSchema questionResponseSchema;
        if (response.isSuccessful() && (questionResponseSchema = response.body()) != null) {
            String questionBody = questionResponseSchema.getQuestion().getBody();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                tvQuestionDetail.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
            } else {
                tvQuestionDetail.setText(Html.fromHtml(questionBody));
            }
        } else {
            onFailure(call, new Throwable("Unsuccessful Response"));
        }
    }

    @Override
    public void onFailure(@NonNull Call<SingleQuestionResponseSchema> call, @NonNull Throwable t) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }
}
