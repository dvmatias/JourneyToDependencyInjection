package com.matias.journeytodependencyinjection.common.dependencyinjection;

import android.support.annotation.UiThread;

import com.matias.journeytodependencyinjection.Constants;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit retrofit;
    private StackoverflowApi stackoverflowApi;

    @UiThread
    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    @SuppressWarnings("SpellCheckingInspection")
    @UiThread
    private StackoverflowApi getStackoverflowApi() {
        if (this.stackoverflowApi == null) {
            this.stackoverflowApi = getRetrofit().create(StackoverflowApi.class);
        }
        return this.stackoverflowApi;
    }

    @UiThread
    FetchQuestionsListInteractor getFetchQuestionsListInteractor() {
        return new FetchQuestionsListInteractor(getStackoverflowApi());
    }

    @UiThread
    FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor() {
        return  new FetchQuestionDetailsInteractor(getStackoverflowApi());
    }
}