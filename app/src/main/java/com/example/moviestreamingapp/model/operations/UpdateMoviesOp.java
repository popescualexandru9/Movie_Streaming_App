package com.example.moviestreamingapp.model.operations;

import android.os.AsyncTask;
import com.example.moviestreamingapp.MyApplication;
import com.example.moviestreamingapp.model.MovieModel;

public class UpdateMoviesOp extends AsyncTask<MovieModel, Void, String> {
    MovieOperations listener;

    public UpdateMoviesOp(MovieOperations listener){
        this.listener= listener;
    }

    @Override
    protected String doInBackground(MovieModel... movies) {
        try {
            MyApplication.getAppDatabase().
                    movieDao().
                    updateMovies(movies);
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

