package com.jameskelly.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jameskelly.popularmovies.MovieGridAdapter.MovieClickListener;
import com.jameskelly.popularmovies.model.Review;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

  List<Review> reviews;
  private static MovieClickListener listener;

  public ReviewAdapter(List<Review> reviews, MovieClickListener listener) {
    this.reviews = reviews;
    this.listener = listener;
  }

  @Override public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);

    return new ReviewViewHolder(view);
  }

  @Override public void onBindViewHolder(ReviewViewHolder holder, int position) {

    holder.reviewAuthor.setText(reviews.get(position).getAuthor());
    holder.reviewPreview.setText(reviews.get(position).getContent());
  }

  @Override public int getItemCount() {
    return reviews.size();
  }

  public static class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.review_author) TextView reviewAuthor;
    @BindView(R.id.review_preview) TextView reviewPreview;

    public ReviewViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      reviewPreview.setEllipsize(TextUtils.TruncateAt.END);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      listener.onClick(v, getLayoutPosition());
    }
  }


}
