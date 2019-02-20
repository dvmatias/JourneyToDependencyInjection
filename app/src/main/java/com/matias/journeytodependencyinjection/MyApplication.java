package com.matias.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private Retrofit retrofit;

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
}