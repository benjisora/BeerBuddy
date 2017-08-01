package com.benjisora.beerbuddy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.benjisora.beerbuddy.AppDatabase;
import com.benjisora.beerbuddy.MyApplication;
import com.benjisora.beerbuddy.R;
import com.benjisora.beerbuddy.model.Bar;
import com.benjisora.beerbuddy.model.Coordinate;
import com.benjisora.beerbuddy.model.Drinker;
import com.benjisora.beerbuddy.model.Meeting;
import com.benjisora.beerbuddy.model.Meeting_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bar bar1 = new Bar("Brasserie Facs", new Coordinate(12, 45));
        bar1.setDescription("Bar sympa situé a coté de la ligne de tram T3");
        bar1.setRating(4);

        bar1.save();

        Drinker drinker1 = new Drinker("John", 21);

        drinker1.setDescription("Buveur de bières entre potes");
        drinker1.setLocation(new Coordinate(20, 14));
        drinker1.setIntake(true);

        Drinker drinker2 = new Drinker("Tony", 21);
        drinker2.setDescription("Jus d'orange = life");
        drinker2.setLocation(new Coordinate(64, 42));
        drinker2.setIntake(false);

        drinker1.createMeeting(bar1, Calendar.getInstance(), Calendar.getInstance());

        //drinker1.addFavoriteBars(bar1);
        //drinker1.addFriends(drinker2);

        Log.d("M"+TAG, drinker1.getMeetingsCreated().toString());


        //drinker1.save();

        //List<Drinker> drinkers = SQLite.select()
          //      .from(Drinker.class)
           //     .queryList();

        //for(Drinker d : drinkers){
        //    Log.d(TAG, d.toString());
       // }

    }
}
