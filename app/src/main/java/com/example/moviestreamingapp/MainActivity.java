package com.example.moviestreamingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moviestreamingapp.fragments.FirstFragment;
import com.example.moviestreamingapp.model.MovieModel;
import com.example.moviestreamingapp.model.operations.GetMoviesOp;
import com.example.moviestreamingapp.model.operations.InsertMovieOp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {
    private static ArrayList<MovieModel> movieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, FirstFragment.class, null)
                    .commit();
        }
    }
}
