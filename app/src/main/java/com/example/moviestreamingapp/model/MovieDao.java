package com.example.moviestreamingapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface MovieDao {
    @Insert
    void insertMovies( MovieModel... movies);

    @Update
    void updateMovies(MovieModel... movies);
    //room uses the primary key to match passed entity instances to rows in the database

    @Delete
    void delete(MovieModel movie);

    @Query("SELECT * FROM moviemodel")
    List<MovieModel> getMovies();

}
