package com.lawl.ui;

/**
 * Created by Garreth on 4/27/2014.
 */
public class ChampDb {
    private long champion_id;
    private String champion_name;

    public long getId() {
        return champion_id;
    }

    public void setId(long id) {
        this.champion_id = id;
    }

    public String getName() {
        return champion_name;
    }

    public void setName(String name) {
        this.champion_name = name;
    }

    @Override
    public String toString() {
        return champion_name;
    }
}
