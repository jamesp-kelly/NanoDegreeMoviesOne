package com.jameskelly.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jameskelly.popularmovies.MovieGridAdapter.MovieClickListener;
import com.jameskelly.popularmovies.api.MovieDbApi;
import com.jameskelly.popularmovies.api.RestClient;
import com.jameskelly.popularmovies.model.Movie;
import com.jameskelly.popularmovies.model.Video;
import com.jameskelly.popularmovies.model.VideoResult;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

  @BindView(R.id.movie_title) TextView movieTitle;
  @BindView(R.id.movie_rating) TextView movieRating;
  @BindView(R.id.movie_poster) ImageView posterView;
  @BindView(R.id.movie_release_date) TextView releaseDate;
  @BindView(R.id.movie_synopsis) TextView synopsis;
  @BindView(R.id.trailers_heading) TextView trailersHeading;
  @BindView(R.id.trailer_recycler_view) RecyclerView trailerRecycler;

  @BindView(R.id.app_bar_layout) AppBarLayout appBarLayout;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @BindView(R.id.backdrop_image) ImageView backdropImageview;
  @BindView(R.id.details_toolbar) Toolbar toolbar;
  @BindView(R.id.scroll_view) NestedScrollView nestedScrollView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_details);
    ButterKnife.bind(this);

    setupToolbar();

    Intent intent = getIntent();
    Movie movie = intent.getParcelableExtra(Movie.MOVIE_EXTRA);

    if (movie != null) {

      toolbar.setTitle(movie.getName());

      collapsingToolbarLayout.setTitle(movie.getName());
      collapsingToolbarLayout.setExpandedTitleColor(getResources()
          .getColor(android.R.color.transparent));

      movieTitle.setText(movie.getName());
      movieRating.setText(String.valueOf(movie.getVoteAverage()));
      releaseDate.setText(movie.getReleaseDate());
      synopsis.setText(movie.getSynopsis());

      loadTrailers(movie);

      Picasso.with(this)
          .load(movie.fixImageUrl(movie.getPosterPath()))
          .into(posterView);
      Picasso.with(this)
          .load(movie.fixImageUrl(movie.getBackdropPath()))
          .into(backdropImageview);

    }
  }

  private void loadTrailers(Movie movie) {
    MovieDbApi movieDbApi = RestClient.MovieDbClient();
    final Call<VideoResult> videoResults = movieDbApi.getTrailers(movie.getId(), getString(R.string.api_key));

    videoResults.enqueue(new Callback<VideoResult>() {
      @Override public void onResponse(Call<VideoResult> call, Response<VideoResult> response) {
        if (response.isSuccessful()) {
          List<Video> videos = response.body().getVideos();
          if (videos.size() > 0) {

            //int dimension = getResources().getDimensionPixelSize(R.dimen.trailer_list_item_height);
            //dimension = dimension * videos.size(); //todo less hacky
            //
            //LinearLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(
            //    ViewGroup.LayoutParams.MATCH_PARENT, dimension);

            trailerRecycler.setVisibility(View.VISIBLE);
            trailersHeading.setVisibility(View.VISIBLE);
            displayVideos(videos);
          }
        }
      }

      @Override public void onFailure(Call<VideoResult> call, Throwable t) {

      }
    });
  }

  private void displayVideos(List<Video> videos) {
    TrailerAdapter trailerAdapter = new TrailerAdapter(this, videos, new MovieClickListener() {
      @Override public void onClick(View view, int position) {
        Snackbar.make(view, "You clicked a trailer", Snackbar.LENGTH_SHORT).show();
      }
    });
    trailerRecycler.setAdapter(trailerAdapter);
    trailerRecycler.setLayoutManager(new LinearLayoutManager(this));
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
