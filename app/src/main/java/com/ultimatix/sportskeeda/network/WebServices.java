package com.ultimatix.sportskeeda.network;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ultimatix.sportskeeda.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {


    Application application;

    private static Retrofit retrofit = null;


    public Retrofit getSKClient() {
        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder defaultHttpClient= new OkHttpClient.Builder();
            defaultHttpClient.addInterceptor(logging);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(defaultHttpClient.build())
                    .build();
        }
        return retrofit;
    }

    public WebServices(Application application) {
        this.application = application;
    }

}
