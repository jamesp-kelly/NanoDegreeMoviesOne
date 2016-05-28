package com.jameskelly.popularmovies.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VideoResult {

  @SerializedName("results")
  private List<Video> videos;

  public List<Video> getVideos() {
    return videos;
  }
}
