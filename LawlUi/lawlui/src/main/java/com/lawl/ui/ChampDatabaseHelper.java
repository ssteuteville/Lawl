package com.lawl.ui;

/**
 * Created by Garreth on 4/27/2014.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChampDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE = "champions";
    public static final String COLUMN_ID = "champion_id";
    public static final String COLUMN_NAME = "champion_name";

    private static final String DATABASE_NAME = "champions.db";
    private static final int DATABASE_VERSION = 1;

    //Database Creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE
            + "(" + COLUMN_ID + " integer primary key, " + COLUMN_NAME
            + " text not null);";

    public ChampDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ChampDatabaseHelper.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }



}