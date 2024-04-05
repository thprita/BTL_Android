package com.example.myapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Review;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Review> reviewlist;
    private final Context context;
    Data data;

    public CommentAdapter(Context context) {
        this.reviewlist = reviewlist;
        this.context = context;
        this.data = new Data(context);
    }
    public void setData(List<Review> reviewList) {
        this.reviewlist = reviewList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText,tvtitlecomment;

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.item_image);
            itemText = view.findViewById(R.id.item_text);
            tvtitlecomment = view.findViewById(R.id.tvtitlefilm);
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
      //  Movie movie = new Movie();
        holder.itemText.setText(review.getComment());
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        int usern = review.getUser_id();
        String userna = data.getUsername(usern);
        String avatar = data.getAvatar(userna);
        holder.tvtitlecomment.setText(userna);

        Glide.with(context)
                .load(avatar)
                .error(R.drawable.taikhoan)
                .into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return reviewlist.size();
    }


}
