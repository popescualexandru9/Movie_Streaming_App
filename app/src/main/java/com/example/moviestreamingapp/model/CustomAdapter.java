package com.example.moviestreamingapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestreamingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MovieViewHolder> {

    private List<MovieModel> localDataSet;
    public static ClickListeners clickListeners;

    public CustomAdapter(
            List<MovieModel> dataSet,
            ClickListeners listener
    ) {
        clickListeners = listener;
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView duration;
        private final TextView description;
        private final TextView releaseYear;
        private final TextView genres;
        private final ImageView movieImageCover;
        private final Button infoBtn;
        private final FloatingActionButton playFab;


        public MovieViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.movie_title);
            duration = view.findViewById(R.id.movie_duration);
            description = view.findViewById(R.id.movie_description);
            releaseYear = view.findViewById(R.id.movie_releaseYear);
            genres = view.findViewById(R.id.movie_genre);
            movieImageCover =  view.findViewById(R.id.movie_cover);
            this.infoBtn =  view.findViewById(R.id.movie_infoBtn);
            this.playFab =  view.findViewById(R.id.play_fab);
        }

        public void bind(MovieModel item){
            title.setText(item.getTitle());
            duration.setText(String.format("%s min", item.getDuration().toString()));
            description.setText(item.getShortDesc());
            releaseYear.setText(item.getReleaseDate().trim().substring(item.getReleaseDate().length() - 4));
            genres.setText(item.getGenres());
            movieImageCover.setImageDrawable(ContextCompat.getDrawable(movieImageCover.getContext(), item.getImageCoverId()));

            infoBtn.setOnClickListener(v -> clickListeners.infoButton(item));

            playFab.setOnClickListener(v -> clickListeners.playFab(item));

        }
    }
}
