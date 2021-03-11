package com.makhabatusen.android3_l4_hw.remote;

import com.makhabatusen.android3_l4_hw.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static WeatherAPI instance;

    private RetrofitBuilder() {
    }

    public static WeatherAPI getInstance() {

        if (instance == null)
            instance = buildRetrofit();
        return instance;
    }


    private static WeatherAPI buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherAPI.class);

    }

}
