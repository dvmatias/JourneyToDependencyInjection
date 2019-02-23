package com.matias.journeytodependencyinjection.screens.questionlist;

import android.support.annotation.NonNull;

import com.matias.journeytodependencyinjection.model.networking.QuestionsListResponseSchema;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.model.ui.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchQuestionsListInteractor implements Callback<QuestionsListResponseSchema> {

    private Call<QuestionsListResponseSchema> call;
    private StackoverflowApi stackoverflowApi;
    private Callback listener;

    // Callback interface.
    interface Callback {
        void onResponse(List<Question> questionList);
        void onFailure();
    }

    // Constructor.
    public FetchQuestionsListInteractor(StackoverflowApi stackoverflowApi) {
        this.stackoverflowApi = stackoverflowApi;
    }

    // Send API request to fetch questions list.
    void fetch(Callback listener) {
        this.listener = listener;
        cancel();
        call = stackoverflowApi.lastActiveQuestions(20);
        call.enqueue(this);
    }

    // Cancel current API request.
    private void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public void onResponse(@NonNull Call<QuestionsListResponseSchema> call,
                           @NonNull Response<QuestionsListResponseSchema> response) {
        if (response.isSuccessful() && response.body() != null &&
                response.body().getQuestions() != null) {
            listener.onResponse(response.body().getQuestions());
        } else {
            listener.onFailure();
        }
    }

    @Override
    public void onFailure(@NonNull Call<QuestionsListResponseSchema> call, @NonNull Throwable t) {
        listener.onFailure();
    }
}