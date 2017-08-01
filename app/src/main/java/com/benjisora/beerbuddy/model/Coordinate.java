package com.benjisora.beerbuddy.model;

import com.benjisora.beerbuddy.AppDatabase;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by saugues on 01/08/17.
 */
@Table(database = AppDatabase.class)
public class Coordinate extends BaseModel{

    @PrimaryKey
    private double latitude;

    @PrimaryKey
    private double longitude;

    public Coordinate(double lat, double lon){
        this.latitude = lat;
        this.longitude = lon;
    }

    public Coordinate(){

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
