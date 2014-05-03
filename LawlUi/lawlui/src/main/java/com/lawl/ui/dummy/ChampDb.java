package com.lawl.ui.dummy;

/**
 * Created by Garreth on 4/27/2014.
 * Was going to use this as part of a DOA (data access object) to help to manage the data for us.
 * The DAO is responsible for handling the database connection and for accessing and modifying the data.
 * It will also convert the database objects into real Java Objects,
 * so that our user interface code does not have to deal with the persistence layer.
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
