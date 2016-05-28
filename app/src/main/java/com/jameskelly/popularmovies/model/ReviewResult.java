package com.jameskelly.popularmovies.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReviewResult {
  @SerializedName("results")
  private List<Review> reviews;

  public List<Review> getReviews() {
    return reviews;
  }
}
