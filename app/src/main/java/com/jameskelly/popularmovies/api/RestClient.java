package com.jameskelly.popularmovies.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

  public static MovieDbApi MovieDbClient() {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(MovieDbApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    return retrofit.create(MovieDbApi.class);
  }

}
