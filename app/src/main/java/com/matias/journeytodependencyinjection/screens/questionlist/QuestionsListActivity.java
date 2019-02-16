package com.matias.journeytodependencyinjection.screens.questionlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.matias.journeytodependencyinjection.Constants;
import com.matias.journeytodependencyinjection.R;
import com.matias.journeytodependencyinjection.networking.QuestionsListResponseSchema;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.questions.Question;
import com.matias.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity
        implements Callback<QuestionsListResponseSchema> {

    /**
     * Recycler view to display questions list.
     */
    private RecyclerView rvQuestions;
    /**
     * Questions list adapter for {@link QuestionsListActivity#rvQuestions}.
     */
    private QuestionsAdapter questionsAdapter;
    /**
     * StackOverflow Api.
     */
    private StackoverflowApi stackoverflowApi;
    /**
     * Call object to send a request to {@link QuestionsListActivity#stackoverflowApi}.
     */
    private Call<QuestionsListResponseSchema> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init recycler view
        rvQuestions = findViewById(R.id.rv_questions);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        // init recycler view adapter.
        questionsAdapter = new QuestionsAdapter(new OnQuestionClickListener() {
            @Override
            public void onQuestionClicked(Question question) {
                QuestionDetailsActivity.start(QuestionsListActivity.this, question.getId());
            }
        });
        rvQuestions.setAdapter(questionsAdapter);

        // init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackoverflowApi = retrofit.create(StackoverflowApi.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // send request for last active question to StackOverflow Api.
        call = stackoverflowApi.lastActiveQuestions(20);
        call.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (call != null) {
            // cancel request for last active question to StackOverflow Api.
            call.cancel();
        }
    }

    @Override
    public void onResponse(@NonNull Call<QuestionsListResponseSchema> call,
                           @NonNull Response<QuestionsListResponseSchema> response) {
        QuestionsListResponseSchema responseSchema;
        if (response.isSuccessful() && (responseSchema = response.body()) != null) {
            questionsAdapter.bindData(responseSchema.getQuestions());
        } else {
            onFailure(call, new Throwable("Unsuccessful Response"));
        }
    }

    @Override
    public void onFailure(@NonNull Call<QuestionsListResponseSchema> call, @NonNull Throwable t) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }

    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }
}
