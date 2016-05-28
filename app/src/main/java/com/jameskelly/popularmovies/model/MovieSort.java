package com.jameskelly.popularmovies.model;

public enum MovieSort {
  POPULAR(0),
  RATING(1);

  private final int value;
  private MovieSort(int value) {
    this.value = value;
  }
  public int getValue() {
    return value;
  }

  public static MovieSort fromInt(int x) {
    switch (x) {
      case 0:
        return POPULAR;
      case 1:
        return RATING;
      default:
        return POPULAR;
    }
  }
}
