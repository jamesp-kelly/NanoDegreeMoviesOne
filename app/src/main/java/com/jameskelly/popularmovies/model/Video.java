package com.jameskelly.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {

  private static final String YOUTUBE = "YouTube";

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
