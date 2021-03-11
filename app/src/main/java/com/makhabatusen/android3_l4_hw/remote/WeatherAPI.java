package com.makhabatusen.android3_l4_hw.remote;

import com.makhabatusen.android3_l4_hw.model.WeatherByCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET(EndPointsAPI.GET_WEATHER)
    Call<WeatherByCity> getWeather
            (@Query(EndPointsAPI.QUERY) String cityName,
             @Query(EndPointsAPI.APP_ID) String appId,
             @Query(EndPointsAPI.UNITS) String units);


}

