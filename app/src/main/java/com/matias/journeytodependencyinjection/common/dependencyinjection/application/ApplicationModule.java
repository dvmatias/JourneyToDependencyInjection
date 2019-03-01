package com.matias.journeytodependencyinjection.common.dependencyinjection.application;

import com.matias.journeytodependencyinjection.Constants;
import com.matias.journeytodependencyinjection.networking.StackoverflowApi;
import com.matias.journeytodependencyinjection.screens.questiondetails.FetchQuestionDetailsInteractor;
import com.matias.journeytodependencyinjection.screens.questionlist.FetchQuestionsListInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    StackoverflowApi getStackoverflowApi(Retrofit retrofit) {
        return retrofit.create(StackoverflowApi.class);
    }

    @Provides
    FetchQuestionsListInteractor getFetchQuestionsListInteractor(
            StackoverflowApi stackoverflowApi) {
        return new FetchQuestionsListInteractor(stackoverflowApi);
    }

    @Provides
    FetchQuestionDetailsInteractor getFetchQuestionDetailsInteractor(
            StackoverflowApi stackoverflowApi) {
        return  new FetchQuestionDetailsInteractor(stackoverflowApi);
    }
}
