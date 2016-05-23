package com.jameskelly.popularmoviesstageone.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.jameskelly.popularmoviesstageone.api.MovieDbApi;

public class Movie implements Parcelable {

  public static final String MOVIE_EXTRA = "movie_extra";

  @SerializedName("title")
  private String name;

  @SerializedName("poster_path")
  private String posterPath;

  @SerializedName("backdrop_path")
  private String backdropPath;

  private double popularity;

  @SerializedName("vote_average")
  private double voteAverage;

  @SerializedName("overview")
  private String synopsis;

  @SerializedName("release_date")
  private String releaseDate;

  private boolean video;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public double getPopularity() {
    return popularity;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public boolean hasVideo() {
    return video;
  }

  public String fixImageUrl(String relativeImageUrl) {
    return MovieDbApi.POSTER_BASE_URL +
        MovieDbApi.POSTER_DEFAULT_SIZE + relativeImageUrl;
  }

  static final Parcelable.Creator<Movie> CREATOR =
      new Parcelable.Creator<Movie>() {

        @Override public Movie createFromParcel(Parcel source) {
          return new Movie(source);
        }

        @Override public Movie[] newArray(int size) {
          return new Movie[size];
        }
      };

  private Movie(Parcel in) {
    this.name = in.readString();
    this.posterPath = in.readString();
    this.backdropPath = in.readString();
    this.popularity = in.readDouble();
    this.voteAverage = in.readDouble();
    this.video = in.readInt() == 0;
    this.releaseDate = in.readString();
    this.synopsis = in.readString();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(posterPath);
    dest.writeString(backdropPath);
    dest.writeDouble(popularity);
    dest.writeDouble(voteAverage);
    dest.writeInt(video ? 0 : 1);
    dest.writeString(releaseDate);
    dest.writeString(synopsis);
  }
}
