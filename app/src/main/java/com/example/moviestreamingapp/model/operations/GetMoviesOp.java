package com.example.moviestreamingapp.model.operations;

import android.os.AsyncTask;

import com.example.moviestreamingapp.MyApplication;
import com.example.moviestreamingapp.model.MovieModel;
import java.util.ArrayList;
import java.util.List;

public class GetMoviesOp extends AsyncTask<Void, Integer, ArrayList<MovieModel>> {
    ArrayList<MovieModel> movies;
    MovieOperations listener;

    public GetMoviesOp(MovieOperations listener){
        this.listener = listener;
    }

    @Override
    protected ArrayList<MovieModel> doInBackground(Void... arg0) {
        try {
            List<MovieModel> movieList  = MyApplication.getAppDatabase().movieDao().getMovies();
            movies = new ArrayList<>(movieList);
        } catch(Exception e){
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieModel> movies){

        listener.getMovies(movies);
    }

}
