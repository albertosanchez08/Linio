package com.alberto.myapplication.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    public static String BASE_URL = "https://raw.githubusercontent.com/linio-mobile-devs/linio-test-files/main/";

    private static Retrofit retrofit;

    public static Retrofit getRetroClient() {
        if(retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
