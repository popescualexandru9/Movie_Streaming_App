package com.example.moviestreamingapp.fragments;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.moviestreamingapp.BuildConfig;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.StreamingMovieActivity;
import com.example.moviestreamingapp.model.MovieModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class SecondFragment extends Fragment {

    public SecondFragment(){

    }

        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        com.example.moviestreamingapp.databinding.FragmentSecondBinding dataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_second,
                container,
                false);

        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();

        MovieModel movie ;
        if (bundle != null) {

             movie = bundle.getParcelable("movie");

            ((TextView) view.findViewById(R.id.detail_movie_title)).setText(movie.getTitle());
            ((TextView) view.findViewById(R.id.detail_movie_desc)).setText(movie.getFullDesc());
            ((TextView) view.findViewById(R.id.detail_movie_release)).append(movie.getReleaseDate());
            ((TextView) view.findViewById(R.id.detail_movie_runtime)).append(movie.getDuration().toString() + " min");
            ((TextView) view.findViewById(R.id.detail_movie_genres)).append( movie.getGenres());

            ImageView movieCoverImg =  view.findViewById(R.id.detail_movie_cover);
            ImageView movieThumbnailImg =  view.findViewById(R.id.detail_movie_img);
            Glide.with(this).load(movie.getImageCoverId()).into(movieCoverImg);
            Glide.with(this).load(movie.getImageThumbnailId()).into(movieThumbnailImg);

            getActivity().setTitle(movie.getTitle());


            FloatingActionButton playBtn = view.findViewById(R.id.play_fab);
            playBtn.setOnClickListener(v -> {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(), BuildConfig.GOOGLE_API_KEY, movie.getVideoId() , 0, true, true);
                startActivity(intent);
            });
        }
    }
}
