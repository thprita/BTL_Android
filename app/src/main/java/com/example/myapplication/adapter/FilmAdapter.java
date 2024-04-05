package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.data.Movie;
import com.example.myapplication.user.chitietfilm;
import com.example.myapplication.user.searchfilm_activity;

import java.util.List;

public class FilmAdapter  extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
  searchfilm_activity searchfilm_activity;
    private List<Movie> filmList;
    private final Context context;
    private OnMovieClickListener onMovieClickListener;

    public FilmAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<Movie> filmList) {
        this.filmList = filmList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        private OnMovieClickListener onMovieClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.tvtitlefilm);
            itemView.setOnClickListener(this);

        }
        public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
            this.onMovieClickListener = onMovieClickListener;
        }
        @Override
        public void onClick(View v) {
            onMovieClickListener.onMovieClick(v,getAdapterPosition(),false);
        }
    }
    @NonNull
    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
        return new FilmAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.ViewHolder holder, int position) {
        searchfilm_activity = new searchfilm_activity();
        Movie movie = filmList.get(position);
        Glide.with(context)
                .load(movie.getImageUrl())
                .error(R.drawable.background) // error_image là resource ID của ảnh hiển thị khi có lỗi
                .into(holder.imageView);

        holder.textView.setText(movie.getTitle());
        holder.setOnMovieClickListener(new OnMovieClickListener() {
            @Override
            public void onMovieClick(View view, int position, boolean isclick) {
                if(!isclick){
                    SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    String username = sharedPreferences.getString("USERNAME", "");
                    Intent intent = new Intent(context, chitietfilm.class);
                    intent.putExtra("MOVIE_ID",movie.getIdmovie());
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("MOVIE_TITLE", movie.getTitle());
                    intent.putExtra("MOVIE_IMAGE", movie.getImageUrl());
                    intent.putExtra("MOVIE_VIDEO", movie.getVideoUrl());
                    intent.putExtra("MOVIE_DESCRIPTION", movie.getDescription());
                    intent.putExtra("MOVIE_CATEGORY",movie.getCategory());
                    intent.putExtra("MOVIE_DURATION", movie.getDuration());

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }
}
