package com.example.moviestreamingapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.moviestreamingapp.model.MovieDao;
import com.example.moviestreamingapp.model.MovieModel;

@Database(entities = {MovieModel.class}, version = 1)
public abstract class Context extends RoomDatabase {

    public abstract MovieDao movieDao();
}
