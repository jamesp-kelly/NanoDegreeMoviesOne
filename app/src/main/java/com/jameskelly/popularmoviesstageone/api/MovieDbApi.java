package com.jameskelly.popularmoviesstageone.api;

import com.jameskelly.popularmoviesstageone.model.MovieDBResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbApi {

  String BASE_URL = "https://api.themoviedb.org/";
  public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
  public static final String POSTER_DEFAULT_SIZE = "w500";

  @GET("/3/movie/popular")
  Call<MovieDBResult> getPopularMovies(@Query("api_key") String apiKey);

  @GET("/3/movie/top_rated")
  Call<MovieDBResult> getTopRatedMovies(@Query("api_key") String apiKey);

}
