package com.lawl.ui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shane Steuteville on 4/26/2014.
 */
public class ScoutProfile implements Parcelable{
    private String name;
    private int rankedWins;
    private String previousRank;
    private String currentRank;
    private String masteryInfo;

    public ScoutProfile(String name, String previousRank, String currentRank, String masteryInfo, int rankedWins )
    {
        this.name = name;
        this.previousRank = previousRank;
        this.rankedWins = rankedWins;
        this.currentRank = currentRank;
        this.masteryInfo = masteryInfo;
    }

    public ScoutProfile(CurrentSeason cur_season, String previousRank, String masteryInfo)
    {
        if(cur_season == null)
            this.name="DUMMY123456";
        else
        {
            this.name = cur_season.name;
            this.previousRank = previousRank;
            this.rankedWins = cur_season.wins;
            this.currentRank = cur_season.cur_rank;
            this.masteryInfo = masteryInfo;
        }
    }

    public ScoutProfile(CurrentSeason cur_season, Masteries masteries, String previousRank)
    {
        if(cur_season == null)
        {
            this.name="Failed to load current stats";
            this.masteryInfo = masteries.toString();
        }
        else if(masteries == null)
        {
            this.name = cur_season.name;
            this.rankedWins = cur_season.wins;
            this.currentRank = cur_season.cur_rank;
            this.masteryInfo = "Failed.";
        }
        else
        {
            this.name = cur_season.name;
            this.previousRank = previousRank;
            this.rankedWins = cur_season.wins;
            this.currentRank = cur_season.cur_rank;
            this.masteryInfo = masteries.toString();
        }
    }

    public String getName()
    {
        return name;
    }

    public int getRankedWins()
    {
        return rankedWins;
    }

    public String getPreviousRank()
    {
        return previousRank;
    }

    public String getCurrentRank()
    {
        return currentRank;
    }

    public String getMasteryInfo()
    {
        return masteryInfo;
    }

    public void setName(String name) { this.name = name; }

    public void setMasteryInfo(String info) { this.masteryInfo = info; }

    public void setRankedWins(int wins) { this.rankedWins = wins; }

    public void setCurrentRank(String rank) { this.currentRank = rank; }

    public void setPreviousRank(String rank) { this.previousRank = rank; }

    public ScoutProfile(Parcel in)
    {
        String[] strings = new String[4];
        in.readStringArray(strings);
        this.name = strings[0];
        this.previousRank = strings[1];
        this.currentRank = strings[2];
        this.masteryInfo = strings[3];
        this.rankedWins = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {this.name, this.previousRank, this.currentRank, this.masteryInfo});
        parcel.writeInt(this.rankedWins);
    }

    public static final Creator CREATOR = new Creator(){
        public ScoutProfile createFromParcel(Parcel in){
            return new ScoutProfile(in);
        }
        public ScoutProfile[] newArray(int size) {
            return new ScoutProfile[size];
        }
    };
}

class CurrentSeason
{
    public String name;
    public String cur_rank;
    public int wins;
    public CurrentSeason(String name, String cur_rank, int wins)
    {
        this.name = name;
        this.cur_rank = cur_rank;
        this.wins = wins;
    }
}

class Masteries
{
    public int page1;
    public int page2;
    public int page3;
    public Masteries(int page1, int page2, int page3)
    {
        this.page1 = page1;
        this.page2 = page2;
        this.page3 = page3;
    }
    public String toString()
    {
        return page1 + "/" + page2 + "/" + page3;
    }
}