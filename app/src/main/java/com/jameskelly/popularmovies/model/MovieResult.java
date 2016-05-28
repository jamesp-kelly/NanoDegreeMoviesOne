package com.jameskelly.popularmovies.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResult {
  @SerializedName("results")
  private List<Movie> movies;

  @SerializedName("total_results")
  private int totalMovies;

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }

  public int getTotalMovies() {
    return totalMovies;
  }

  public void setTotalMovies(int totalMovies) {
    this.totalMovies = totalMovies;
  }
}
