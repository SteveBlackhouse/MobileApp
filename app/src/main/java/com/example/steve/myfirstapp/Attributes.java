package com.example.steve.myfirstapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("favoritesCount")
    @Expose
    private String favoritesCount;

    @SerializedName("startDate")
    @Expose
    private String startDate;

    @SerializedName("endDate")
    @Expose
    private String endDate;

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("ageRating")
    @Expose
    private String ageRating;

    @SerializedName("coverImage")
    @Expose
    private PosterImage coverImage;

    @Override
    public String toString() {
        return "Attributes{" +
                "favoritesCount='" + favoritesCount + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", coverImage=" + coverImage +
                '}';
    }

    public PosterImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(PosterImage coverImage) {
        this.coverImage = coverImage;
    }

    public String getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(String favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }
}
