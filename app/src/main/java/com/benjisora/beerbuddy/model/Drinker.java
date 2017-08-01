package com.benjisora.beerbuddy.model;

import com.benjisora.beerbuddy.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.MultipleManyToMany;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by saugues on 31/07/17.
 */

@Table(database = AppDatabase.class)
@MultipleManyToMany({
        @ManyToMany(referencedTable = Drinker.class),
        @ManyToMany(referencedTable = Bar.class)
})
public class Drinker extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String username;

    @Column
    private int age;

    @ForeignKey(stubbedRelationship = true)
    private Coordinate location;

    @Column
    private String description;

    @Column
    private float rating;

    @Column
    private boolean intake;

    private List<Bar> favoriteBars;

    private List<Drinker> friendList;

    List<Meeting> meetingsCreated;

    private List<Meeting> meetingsSubscribed;


    public Drinker(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Drinker() {

    }

    //region getters/setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isIntake() {
        return intake;
    }

    public void setIntake(boolean intake) {
        this.intake = intake;
    }

    @OneToMany
    public List<Bar> getFavoriteBars() {
        if (favoriteBars == null) {
            List<Drinker_Bar> list = SQLite.select().from(Drinker_Bar.class)
                    .where(Drinker_Bar_Table.drinker_id.eq(id)).queryList();
            favoriteBars = new ArrayList<>();
            for (Drinker_Bar item : list) {
                favoriteBars.add(item.getBar());
            }
        }
        return favoriteBars;
    }

    public void addFavoriteBars(Bar... favoriteBars) {
        for (Bar barToSave : favoriteBars) {
            Drinker_Bar drinkerbar = new Drinker_Bar();
            drinkerbar.setDrinker(this);
            drinkerbar.setBar(barToSave);
            drinkerbar.save();
        }
    }

    public void setFavoriteBars(List<Bar> favoriteBars) {
        this.favoriteBars = favoriteBars;
    }

    @OneToMany
    public List<Drinker> getFriendList() {
        if (friendList == null) {
            List<Drinker_Drinker> list = SQLite.select().from(Drinker_Drinker.class)
                    .where(Drinker_Drinker_Table.drinker0_id.eq(id)).queryList();
            friendList = new ArrayList<>();
            for (Drinker_Drinker item : list) {
                friendList.add(item.getDrinker1());
            }
        }
        return friendList;
    }

    public void addFriends(Drinker... friends) {
        for (Drinker friend : friends) {
            Drinker_Drinker drinkerfriend = new Drinker_Drinker();
            drinkerfriend.setDrinker0(this);
            drinkerfriend.setDrinker1(friend);
            drinkerfriend.save();
        }
    }

    public void setFriendList(List<Drinker> friendList) {
        this.friendList = friendList;
    }

    @OneToMany
    public List<Meeting> getMeetingsCreated() {
        if (meetingsCreated == null || meetingsCreated.isEmpty()) {
            meetingsCreated = SQLite.select()
                    .from(Meeting.class)
                    .where(Meeting_Table.organizer_id.eq(id))
                    .queryList();
        }
        return meetingsCreated;
    }

    public void setMeetingsCreated(List<Meeting> meetingsCreated) {
        this.meetingsCreated = meetingsCreated;
    }

    @OneToMany
    public List<Meeting> getMeetingsSubscribed() {
        if (meetingsSubscribed == null) {
            List<Meeting_Drinker> list = SQLite.select().from(Meeting_Drinker.class)
                    .where(Meeting_Drinker_Table.drinker_id.eq(id)).queryList();
            meetingsSubscribed = new ArrayList<>();
            for (Meeting_Drinker item : list) {
                meetingsSubscribed.add(item.getMeeting());
            }
        }
        return meetingsSubscribed;
    }

    public void addMeetingsSubscribed(Meeting... meetings) {
        for (Meeting meeting : meetings) {
            Meeting_Drinker meetingDrinker = new Meeting_Drinker();
            meetingDrinker.setDrinker(this);
            meetingDrinker.setMeeting(meeting);
            meetingDrinker.save();
        }
    }

    public void setMeetingsSubscribed(List<Meeting> meetingsSubscribed) {
        this.meetingsSubscribed = meetingsSubscribed;
    }

    //endregion

    public void createMeeting(Bar bar, Calendar startDate, Calendar endDate) {

        Meeting meeting = new Meeting(bar, startDate, endDate, this);
        meeting.save();

        Meeting_Drinker meetingDrinker = new Meeting_Drinker();
        meetingDrinker.setDrinker(this);
        meetingDrinker.setMeeting(meeting);
        meetingDrinker.save();
    }

}
