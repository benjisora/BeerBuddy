package com.benjisora.beerbuddy.model;

import android.graphics.Bitmap;
import android.location.Location;

import com.benjisora.beerbuddy.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * Created by saugues on 31/07/17.
 */

@Table(database = AppDatabase.class)
public class Bar extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    @NotNull
    private String name;

    @Column
    private String description;

    @ForeignKey(stubbedRelationship = true)
    @NotNull
    private Coordinate location;

    private List<Bitmap> pictures;

    @Column
    private float rating;

    public Bar(String name, Coordinate location) {
        this.name = name;
        this.location = location;
    }

    public Bar(){

    }

    // Region getters/setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public List<Bitmap> getPictures() {
        return pictures;
    }

    public void setPictures(List<Bitmap> pictures) {
        this.pictures = pictures;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    // End Region
}
