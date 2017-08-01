package com.benjisora.beerbuddy.model;

import com.benjisora.beerbuddy.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by saugues on 31/07/17.
 */

@Table(database = AppDatabase.class)
@ManyToMany(referencedTable = Drinker.class)
public class Meeting extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @ForeignKey(tableClass = Bar.class,
            references = @ForeignKeyReference(columnName = "bar", foreignKeyColumnName = "id"))
    @NotNull
    private Bar bar;

    @Column
    @NotNull
    private Calendar startDate;

    @Column
    @NotNull
    private Calendar endDate;

    @Column
    private String description;

    @Column
    private int maxParticipantsNumber;

    @Column
    private int currentParticipantsNumber;

    @Column
    private boolean offeredDrinks;

    @ForeignKey(stubbedRelationship = true)
    @NotNull
    private Drinker organizer;

    private List<Drinker> participants;

    public Meeting(Bar bar, Calendar startDate, Calendar endDate, Drinker organizer) {
        this.bar = bar;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizer = organizer;

        if (this.participants == null)
            this.participants = new ArrayList<>();

        this.participants.add(organizer);

        this.currentParticipantsNumber = 1;
    }

    public Meeting(){

    }

    //region getters/setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxParticipantsNumber() {
        return maxParticipantsNumber;
    }

    public void setMaxParticipantsNumber(int maxParticipantsNumber) {
        this.maxParticipantsNumber = maxParticipantsNumber;
    }

    public int getCurrentParticipantsNumber() {
        return currentParticipantsNumber;
    }

    public void setCurrentParticipantsNumber(int currentParticipantsNumber) {
        this.currentParticipantsNumber = currentParticipantsNumber;
    }

    public boolean isOfferedDrinks() {
        return offeredDrinks;
    }

    public void setOfferedDrinks(boolean offeredDrinks) {
        this.offeredDrinks = offeredDrinks;
    }

    public Drinker getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Drinker organizer) {
        this.organizer = organizer;
    }

    @OneToMany
    public List<Drinker> getParticipants() {
        if (participants == null) {
            List<Meeting_Drinker> list = SQLite.select().from(Meeting_Drinker.class)
                    .where(Meeting_Drinker_Table.meeting_id.eq(id)).queryList();
            participants = new ArrayList<>();
            for (Meeting_Drinker item: list) {
                participants.add(item.getDrinker());
            }
        }
        return participants;
    }

    public void setParticipants(List<Drinker> participants) {
        this.participants = participants;
    }

    //endregion
}
