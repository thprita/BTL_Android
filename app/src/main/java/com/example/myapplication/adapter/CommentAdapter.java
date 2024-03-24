package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Review;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Review> reviewlist;
    private final Context context;

    public CommentAdapter(List<Review> reviewlist, Context context) {
        this.reviewlist = reviewlist;
        this.context = context;
    }
    public void setData(List<Review> reviewList) {
        this.reviewlist = reviewList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText,itemND;

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.item_image);
            itemText = view.findViewById(R.id.item_text);
            itemND = view.findViewById(R.id.item_nd);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewlist.get(position);
        Movie movie = new Movie();
        holder.itemText.setText(review.getComment());
        Glide.with(context)
                .load(movie.getImageUrl())
                .into(holder.itemImage);
        holder.itemND.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return reviewlist.size();
    }


}
