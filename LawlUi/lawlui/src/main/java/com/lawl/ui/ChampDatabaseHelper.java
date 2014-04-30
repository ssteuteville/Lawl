package com.lawl.ui;

/**
 * Created by Garreth on 4/27/2014.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChampDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE = "champions";
    public static final String COLUMN_ID = "champion_id";
    public static final String COLUMN_NAME = "champion_name";
    public static final String DB_FULL_PATH = "/data/data/com/databases/";

    private static final String DATABASE_NAME = "champions.db";
    private static final int DATABASE_VERSION = 1;
    Context context;

    //Database Creation SQL statement
    private static final String DATABASE_CREATE = "create table if not exists " + TABLE
            + "(" + COLUMN_ID + " integer primary key, " + COLUMN_NAME
            + " text not null);";

    public ChampDatabaseHelper(Context p_context) {
        super(p_context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = p_context;
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

    public boolean checkDatabase() {
        SQLiteDatabase db;
        try {
            //db = SQLiteDatabase.openDatabase(DB_FULL_PATH, null, SQLiteDatabase.OPEN_READONLY);
            //db.close();
            db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM " + TABLE, null);
                if (cur.getCount() == 0) {
                    Log.d("No Data Exists : ", "cool");
                    db.close();
                    return false;
                } else {
                    db.close();
                    return true;
                }
        } catch (SQLiteException e) {
            Log.d("Database doesn't exist", "No database");
            return false;
        }
    }

    public boolean insertChamp(int id, String name) {
        if(checkUniqueName(name)) {
            try{
                SQLiteDatabase insertDatabase = this.getWritableDatabase();
                Log.d("Database Insert: ", "INSERT OR REPLACE INTO " +
                        TABLE + " (champion_id, champion_name) Values ("+ id + ", " + name + ");");
                insertDatabase.execSQL("INSERT OR REPLACE INTO " +
                        TABLE + " (champion_id, champion_name) Values (\""+ id + "\", \"" + name + "\");");
                insertDatabase.close();
                return true;
            }
            catch (SQLiteException ex) {
                Log.d("Database Insert Error: ", ex.toString());
                return false;
            }
        }
        else {
            Log.d("Failed to insert not unique: ", name + id);
            return false;
        }

    }

    public boolean checkUniqueName(String stringCompare) {
        try {
            SQLiteDatabase checkDatabase = this.getReadableDatabase();
            //checkDatabase.execSQL(DATABASE_CREATE);
            Cursor cur = checkDatabase.rawQuery("SELECT * FROM " + TABLE, null);
            if (cur != null) {
                if(cur.moveToFirst()) {
                    do {
                        String text = cur.getString((cur.getColumnIndex("champion_name")));
                        if (stringCompare.equals(text)) {
                            checkDatabase.close();
                            return false;
                        }
                    } while (cur.moveToNext());
                    checkDatabase.close();
                    return true;
                }
            }
            checkDatabase.close();
        } catch (SQLiteException ex) {
            Log.d("Unique Check Error: ", ex.toString());
            return false;
        }
        return true;
    }

    public int getId(String name) {
        int temp_id = -1;
        try{
            SQLiteDatabase readDatabase = this.getReadableDatabase();
            //readDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TEXT_TABLE + " (text VARCHAR);");
            Cursor cur = readDatabase.rawQuery("SELECT champion_id FROM " + TABLE + " WHERE champion_name = "
                    + name, null);

            if (cur != null) {
                if(cur.moveToFirst()) {
                        temp_id = cur.getInt(cur.getColumnIndex("champion_id"));
                }
            }
            readDatabase.close();
            return temp_id;
        }
        catch (SQLiteException ex) {
            Log.d("Champ ID Read Error: ", ex.toString());
            return -1;
        }
    }

    public String getName(int id) {
        String temp_name = "";
        try {
            SQLiteDatabase readDatabase = this.getReadableDatabase();
            Cursor cur = readDatabase.rawQuery("SELECT champion_name FROM " + TABLE
            + " WHERE champion_id = " + id, null);

            if (cur != null) {
                if(cur.moveToFirst()) {
                    temp_name = cur.getString(cur.getColumnIndex("champion_name"));
                }
            }
            readDatabase.close();
            return temp_name;
        } catch (SQLiteException ex) {
            Log.d("Champ Name Read Error: ", ex.toString());
            return "";
        }
    }
}