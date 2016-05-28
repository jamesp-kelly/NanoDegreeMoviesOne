package com.jameskelly.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.jameskelly.popularmovies.model.MovieSort;

public class PreferenceHelper {

  private static final String MOVIE_SORT = "movie_sort";

  public static MovieSort getPreferredSort(Context context) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    int storedValue = preferences.getInt(MOVIE_SORT, 0);

    return MovieSort.fromInt(storedValue);
  }

  public static void setPreferredSort(Context context, MovieSort updatedSort) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    int valueToStore = updatedSort.getValue();

    preferences.edit().putInt(MOVIE_SORT, valueToStore).apply();
  }
}
