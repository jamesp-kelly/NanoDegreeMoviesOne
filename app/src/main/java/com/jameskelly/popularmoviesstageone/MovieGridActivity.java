package com.jameskelly.popularmoviesstageone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jameskelly.popularmoviesstageone.api.MovieDbApi;
import com.jameskelly.popularmoviesstageone.api.RestClient;
import com.jameskelly.popularmoviesstageone.model.Movie;
import com.jameskelly.popularmoviesstageone.model.MovieDBResult;
import com.jameskelly.popularmoviesstageone.model.MovieSort;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieGridActivity extends AppCompatActivity {

  private static final int GRID_COLUMN_COUNT = 2;
  private MovieGridAdapter adapter;

  @BindView(R.id.movie_grid_recycler) RecyclerView movieRecyclerView;
  @BindView(R.id.movie_grid_message) TextView movieGridMessage;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_grid);

    ButterKnife.bind(this);
    setupToolbar();

    loadMovies(PreferenceHelper.getPreferredSort(this));
  }

  private void loadMovies(MovieSort sort) {
    MovieDbApi movieDbApi = RestClient.MovieDbClient();
    String apiKey = getString(R.string.api_key); //gradle file

    final Call<MovieDBResult> displayMovies;

    if (sort == MovieSort.POPULAR) {
      displayMovies = movieDbApi.getPopularMovies(apiKey);
    } else {
      displayMovies = movieDbApi.getTopRatedMovies(apiKey);
    }

    displayMovies.enqueue(new Callback<MovieDBResult>() {
      @Override public void onResponse(Call<MovieDBResult> call, Response<MovieDBResult> response) {
        if (response.isSuccessful()) {
          displayMovies(response.body().getMovies());
        } else {
          displayMovies(null);
        }
      }

      @Override public void onFailure(Call<MovieDBResult> call, Throwable t) {
        displayMovies(null);
      }
    });
  }

  private void displayMovies(List<Movie> movies) {

    if (movies != null && movies.size() > 0) {
      movieRecyclerView.setVisibility(View.VISIBLE);
      movieGridMessage.setVisibility(View.GONE);

      movieRecyclerView.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN_COUNT));
      adapter = new MovieGridAdapter(this, movies, new MovieGridAdapter.MovieClickListener() {
        @Override public void onClick(View view, int position) {
          Movie movie = adapter.getMovie(position);
          Intent intent = new Intent(MovieGridActivity.this, MovieDetailsActivity.class);
          intent.putExtra(Movie.MOVIE_EXTRA, movie);
          startActivity(intent);
        }
      });
      movieRecyclerView.setAdapter(adapter);
    } else {
      movieRecyclerView.setVisibility(View.GONE);
      movieGridMessage.setVisibility(View.VISIBLE);
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);

    MovieSort preferredSort = PreferenceHelper.getPreferredSort(this);
    MenuItem menuItem = menu.findItem(R.id.change_sort);

    if (preferredSort == MovieSort.POPULAR) {
      menuItem.setTitle(getString(R.string.sort_by_rating));
    } else {
      menuItem.setTitle(getString(R.string.sort_by_popularity));
    }

    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.change_sort) {
      MovieSort preferredSort = PreferenceHelper.getPreferredSort(this);

      if (preferredSort == MovieSort.POPULAR) {
        PreferenceHelper.setPreferredSort(this, MovieSort.RATING);
        loadMovies(MovieSort.RATING);
        item.setTitle(getString(R.string.sort_by_popularity));
      } else {
        PreferenceHelper.setPreferredSort(this, MovieSort.POPULAR);
        loadMovies(MovieSort.POPULAR);
        item.setTitle(getString(R.string.sort_by_rating));
      }
    }

    return true;
  }

  private void setupToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }
}
