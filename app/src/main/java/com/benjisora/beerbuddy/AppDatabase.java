package com.benjisora.beerbuddy;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by saugues on 31/07/17.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "BeerBuddy";

    public static final int VERSION = 1;
}
