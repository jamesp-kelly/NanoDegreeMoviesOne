package com.jameskelly.popularmovies.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {

  private static final String YOUTUBE = "YouTube";
  private static final String YOUTUBE_BASE_URL = "http://www.youtube.com/watch?v=";
  private static final String YOUTUBE_THUMB_FORMAT = "http://img.youtube.com/vi/%s/hqdefault.jpg";

  private String id;
  private String name;
  private String site;

  @SerializedName("key")
  private String siteKey;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSite() {
    return site;
  }

  public String getSiteKey() {
    return siteKey;
  }

  public String getThumbnailImage() {
    if (site.equals(YOUTUBE)) {
      return String.format(YOUTUBE_THUMB_FORMAT, siteKey);
    }

    throw new IllegalStateException("Thumbnails are not available for this video type");
  }

  public Uri getVideoUri() {
    if (site.equals(YOUTUBE)) {
      return Uri.parse(YOUTUBE_BASE_URL + siteKey);
    }

    throw new IllegalStateException("Full video URLs are not available for this video type");
  }

  static final Parcelable.Creator<Video> CREATOR =
      new Parcelable.Creator<Video>() {

        @Override public Video createFromParcel(Parcel source) {
          return new Video(source);
        }

        @Override public Video[] newArray(int size) {
          return new Video[size];
        }
      };

  private Video(Parcel in) {
    this.id = in.readString();
    this.name = in.readString();
    this.site = in.readString();
    this.siteKey = in.readString();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
    dest.writeString(site);
    dest.writeString(siteKey);
  }
}
