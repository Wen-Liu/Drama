package com.wenliu.chocolabsexam.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.wenliu.chocolabsexam.object.Drama;

@Entity(tableName = "drama")
public class DramaEntry {

    @PrimaryKey(autoGenerate = true)
    private int dramaId;

    @ColumnInfo(name = "drama_name")
    private String name;

    @ColumnInfo(name = "total_Views")
    private int totalViews;

    @ColumnInfo(name = "created_At")
    private String createdAt;

    @ColumnInfo(name = "drama_thumb")
    private String thumb;

    @ColumnInfo(name = "drama_rating")
    private double rating;

    public DramaEntry(int dramaId, String name, int totalViews, String createdAt, String thumb, double rating) {
        this.dramaId = dramaId;
        this.name = name;
        this.totalViews = totalViews;
        this.createdAt = createdAt;
        this.thumb = thumb;
        this.rating = rating;
    }

    public DramaEntry(Drama drama) {
        this.dramaId = drama.getDramaId();
        this.name = drama.getName();
        this.totalViews = drama.getTotalViews();
        this.createdAt = drama.getCreatedAt();
        this.thumb = drama.getThumb();
        this.rating = drama.getRating();
    }


    public Integer getDramaId() {
        return dramaId;
    }

    public void setDramaId(Integer dramaId) {
        this.dramaId = dramaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
