package com.example.moviestreamingapp.model.operations;

import android.graphics.Movie;
import android.os.AsyncTask;
import com.example.moviestreamingapp.MyApplication;
import com.example.moviestreamingapp.model.MovieModel;

import java.util.ArrayList;

public class InsertMovieOp extends AsyncTask<MovieModel, Void, String> {
    MovieOperations listener;

    public InsertMovieOp(MovieOperations listener){
        this.listener= listener;
    }

    @Override
    protected String doInBackground(MovieModel... movies) {
        try {
            MyApplication.getAppDatabase().
                    movieDao().
                    insertMovies(movies);
        } catch(Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        listener.insertMovies(result);
    }
}

