package com.example.asignment1.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient  {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        if (retrofit == null)

            retrofit =new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        return retrofit;
    }
}