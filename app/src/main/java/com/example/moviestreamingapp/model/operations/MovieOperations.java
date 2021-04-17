package com.example.moviestreamingapp.model.operations;

import com.example.moviestreamingapp.model.MovieModel;

import java.util.ArrayList;

public interface MovieOperations {
    void insertMovies(String result);
    void getMovies(ArrayList<MovieModel> movies);

}
