package com.jameskelly.popularmovies.api;

import com.jameskelly.popularmovies.model.MovieResult;
import com.jameskelly.popularmovies.model.ReviewResult;
import com.jameskelly.popularmovies.model.VideoResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbApi {

  String BASE_URL = "https://api.themoviedb.org/";
  public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
  public static final String POSTER_DEFAULT_SIZE = "w500";

  @GET("/3/movie/popular")
  Call<MovieResult> getPopularMovies(@Query("api_key") String apiKey);

  @GET("/3/movie/top_rated")
  Call<MovieResult> getTopRatedMovies(@Query("api_key") String apiKey);

  @GET("3/movie/{id}/videos")
  Call<VideoResult> getTrailers(@Path("id") String movieId, @Query("api_key") String apiKey);

  @GET("3/movie/{id}/reviews")
  Call<ReviewResult> getReviews(@Path("id") String movieId, @Query("api_key") String apiKey);

}
