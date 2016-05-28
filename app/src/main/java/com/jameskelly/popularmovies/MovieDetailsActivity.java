package com.jameskelly.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jameskelly.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

  @BindView(R.id.movie_title) TextView movieTitle;
  @BindView(R.id.movie_rating) TextView movieRating;
  @BindView(R.id.movie_poster) ImageView posterView;
  @BindView(R.id.movie_release_date) TextView releaseDate;
  @BindView(R.id.movie_synopsis) TextView synopsis;

  @BindView(R.id.app_bar_layout) AppBarLayout appBarLayout;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @BindView(R.id.backdrop_image) ImageView backdropImageview;
  @BindView(R.id.details_toolbar) Toolbar toolbar;
  @BindView(R.id.scroll_view) NestedScrollView nestedScrollView;

  //private Movie movie;

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

      Picasso.with(this)
          .load(movie.fixImageUrl(movie.getPosterPath()))
          .into(posterView);
      Picasso.with(this)
          .load(movie.fixImageUrl(movie.getBackdropPath()))
          .into(backdropImageview);

    }
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
