package com.matias.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import com.matias.journeytodependencyinjection.networking.StackoverflowApi;

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
}