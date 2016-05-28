package com.jameskelly.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jameskelly.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieViewHolder> {

  private List<Movie> movies;
  private Context context;
  private static MovieClickListener listener;

  public MovieGridAdapter(Context context, List<Movie> movies, MovieClickListener listener) {
    this.movies = movies;
    this.context = context;
    this.listener = listener;
  }

  @Override public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_movie_grid, parent, false);

    return new MovieViewHolder(view);
  }

  @Override public void onBindViewHolder(MovieViewHolder holder, int position) {
    Movie movie = movies.get(position);
    Picasso.with(context)
        .load(movie.fixImageUrl(movie.getPosterPath()))
        .into(holder.posterView);
  }

  @Override public int getItemCount() {
    return movies.size();
  }

  public Movie getMovie(int position) {
    return movies.get(position);
  }


  public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.movie_poster) ImageView posterView;

    public MovieViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      listener.onClick(v, getLayoutPosition());
    }
  }

  public interface MovieClickListener {
    void onClick(View view, int position);
  }
}
