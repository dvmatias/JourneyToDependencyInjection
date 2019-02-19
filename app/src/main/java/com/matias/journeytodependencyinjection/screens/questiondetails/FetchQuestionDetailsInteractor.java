package com.matias.journeytodependencyinjection.screens.questiondetails;

import android.support.annotation.NonNull;

import com.matias.journeytodependencyinjection.Constants;
import com.matias.journeytodependencyinjection.networking.SingleQuestionResponseSchema;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchQuestionDetailsInteractor implements Callback<SingleQuestionResponseSchema> {

    private Call<SingleQuestionResponseSchema> call;
    private StackoverflowApi stackoverflowApi;
    private FetchQuestionDetailsInteractor.Callback listener;

    // Callback interface.
    interface Callback {
        void onResponse(String questionDetails);
        void onFailure();
    }

    // Constructor.
    FetchQuestionDetailsInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stackoverflowApi = retrofit.create(StackoverflowApi.class);
    }

    // Send API request to fetch specific question details.
    void fetch(FetchQuestionDetailsInteractor.Callback listener, String questionId) {
        this.listener = listener;
        cancel();
        call = stackoverflowApi.questionDetails(questionId);
        call.enqueue(this);
    }

    // Cancel current API request.
    private void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public void onResponse(@NonNull Call<SingleQuestionResponseSchema> call,
                           @NonNull Response<SingleQuestionResponseSchema> response) {
        if (response.isSuccessful() && response.body() != null &&
                response.body().getQuestion() != null &&
                response.body().getQuestion().getBody() != null) {
            listener.onResponse(response.body().getQuestion().getBody());
        } else {
            listener.onFailure();
        }
    }

    @Override
    public void onFailure(@NonNull Call<SingleQuestionResponseSchema> call, @NonNull Throwable t) {
        listener.onFailure();
    }
}