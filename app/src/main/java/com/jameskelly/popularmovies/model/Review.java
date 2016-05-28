package com.jameskelly.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

  private String id;
  private String author;
  private String content;
  private String url;

  public String getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public String getUrl() {
    return url;
  }

  static final Parcelable.Creator<Review> CREATOR =
      new Parcelable.Creator<Review>() {

    @Override public Review createFromParcel(Parcel source) {
      return new Review(source);
    }

    @Override public Review[] newArray(int size) {
      return new Review[0];
    }
  };

  private Review(Parcel in) {
    id = in.readString();
    author = in.readString();
    content = in.readString();
    url = in.readString();
  }


  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(author);
    dest.writeString(content);
    dest.writeString(url);
  }
}
