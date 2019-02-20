package com.matias.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsPresenterImpl;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListActivity;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListPresenterImpl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private Retrofit retrofit;
    private StackoverflowApi stackoverflowApi;

    @UiThread
    public Retrofit getRetrofit() {
        if (this.retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    @UiThread
    public StackoverflowApi getStackoverflowApi() {
        if (this.stackoverflowApi == null) {
            this.stackoverflowApi = getRetrofit().create(StackoverflowApi.class);
        }
        return this.stackoverflowApi;
    }

    @UiThread
    private FetchQuestionsListInteractor getFetchQuestionsListInteractor() {
        return new FetchQuestionsListInteractor(getStackoverflowApi());
    }

    @UiThread
    private FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor() {
        return  new FetchQuestionDetailsInteractor(getStackoverflowApi());
    }

    @UiThread
    public QuestionsListPresenterImpl getQuestionsListPresenterImpl(QuestionsListActivity view) {
        return new QuestionsListPresenterImpl(view, getFetchQuestionsListInteractor());
    }

    @UiThread
    public QuestionDetailsPresenterImpl getQuestionDetailsPresenterImpl(QuestionDetailsActivity view) {
        return new QuestionDetailsPresenterImpl(view, getFetchQuestionDetailsInteractor());
    }
}