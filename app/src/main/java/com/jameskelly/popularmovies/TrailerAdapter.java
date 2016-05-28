package com.jameskelly.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jameskelly.popularmovies.MovieGridAdapter.MovieClickListener;
import com.jameskelly.popularmovies.model.Video;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

  private final Context context;
  private final List<Video> trailers;
  private static MovieClickListener listener;

  public TrailerAdapter(Context context, List<Video> trailers, MovieClickListener listener) {
    this.context = context;
    this.trailers = trailers;
    this.listener = listener;
  }

  @Override public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_trailer, parent, false);

    return new TrailerViewHolder(view);
  }

  @Override public void onBindViewHolder(TrailerViewHolder holder, int position) {
    Video trailer = trailers.get(position);

    holder.trailerName.setText(trailer.getName());
    Picasso.with(context).load(trailer.getThumbnailImage()).into(holder.trailerThumbnail);
  }

  @Override public int getItemCount() {
    return trailers.size();
  }

  public static class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.trailer_thumbnail) ImageView trailerThumbnail;
    @BindView(R.id.trailer_name) TextView trailerName;

    public TrailerViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      listener.onClick(v, getLayoutPosition());
    }
  }
}
