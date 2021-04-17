package com.example.moviestreamingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class MovieModel implements Parcelable {


    // make fields private
    @PrimaryKey
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private Integer duration;
    @NonNull
    private String shortDesc;
    @NonNull
    private String fullDesc;
    @NonNull
    private Integer imageThumbnailId;
    @NonNull
    private Integer imageCoverId;

    @NonNull
    private String releaseDate;
    @NonNull
    private String genres;
    @NonNull
    private String videoId;

    public MovieModel() {
    };

    public MovieModel(Integer id, @NonNull String title, @NonNull Integer duration, @NonNull String shortDesc,
                      @NonNull String fullDesc, @NonNull Integer imageThumbnailId, @NonNull Integer imageCoverId,
                      @NonNull String releaseDate, @NonNull String[] genres, @NonNull String videoId) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.imageThumbnailId = imageThumbnailId;
        this.imageCoverId = imageCoverId;
        this.releaseDate = releaseDate;
        this.genres = TextUtils.join(" · ", genres);
        this.videoId = videoId;

    }

    public MovieModel(Parcel in){
        String[] data = new String[10];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.id= Integer.valueOf(data[0]);
        this.title = data[1];
        this.duration = Integer.valueOf(data[2]);
        this.shortDesc = data[3];
        this.fullDesc = data[4];
        this.releaseDate = data[5];
        this.genres = data[6];
        this.videoId = data[7];
        this.imageThumbnailId = Integer.valueOf(data[8]);
        this.imageCoverId = Integer.valueOf(data[9]);

    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {
                this.id.toString(), this.title, this.duration.toString(), this.shortDesc,
                this.fullDesc, this.releaseDate, this.genres, this.videoId,
                this.imageThumbnailId.toString(), this.imageCoverId.toString()});
    }

    public static final Creator CREATOR = new Creator() {
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(@NonNull Integer duration) {
        this.duration = duration;
    }

    @NonNull
    public String getShortDesc() {
        return shortDesc;
    }
    @NonNull
    public String getFullDesc() {
        return fullDesc;
    }

    public void setShortDesc(@NonNull String description) {
        this.shortDesc = description;
    }

    public void setFullDesc(@NonNull String description) {
        this.fullDesc = description;
    }

    @NonNull
    public Integer getImageThumbnailId() {
        return imageThumbnailId;
    }

    public void setImageThumbnailId(@NonNull Integer imageThumbnailId) {
        this.imageThumbnailId = imageThumbnailId;
    }

    @NonNull
    public Integer getImageCoverId() {
        return imageCoverId;
    }

    public void setImageCoverId(@NonNull Integer imageCover) {
        this.imageCoverId = imageCover;
    }
    @NonNull
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NonNull String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NonNull
    public String getGenres() {
        return genres;
    }

    public void setGenres(@NonNull String genres) {
        this.genres = genres;
    }

    @NonNull
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(@NonNull String videoId) {
        this.videoId = videoId;
    }

    //endregion
}
