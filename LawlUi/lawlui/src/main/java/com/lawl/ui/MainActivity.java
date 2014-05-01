package com.lawl.ui;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends ActionBarActivity implements MainFragment.OnButtonPressListener, ChampFragment.OnChampClickListener, ScouterFragment.OnScoutActionListener {

    ChampDatabaseHelper dbHelper;
    //SQLiteDatabase db;
    RiotApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiating database/checking to see if it needs to be updated
        // Still need to implement update of database if more current data is available.
        dbHelper = new ChampDatabaseHelper(this);
        client = new RiotApiClient("0b63c21d-b03a-4c25-b481-57d853f29a08");
        Log.d("IF STATEMENT", "BEFORE");
        if (!dbHelper.checkDatabase()) {
            Log.d("IF STATEMENT", "DURING");
            //dbHelper.onCreate(this);
            String url = String.format("/api/lol/static-data/%s/v1.2/champion?locale=en_US&champData=info&", "na");

            client.get(url, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONObject champs = response.getJSONObject("data");
                        JSONArray names = champs.names();
                        for(int i = 0; i < names.length(); i++) {
                            String champ_name = names.get(i).toString();
                            String id_s = champs.getJSONObject(champ_name).get("id").toString();
                            int id = Integer.parseInt(id_s);
                            //insert into database
                            boolean temp = dbHelper.insertChamp(id, champ_name);
                            if (temp) {
                                Log.d("Champion Inserted: ", champ_name + " " + id);
                            }
                            else Log.d("Champion not inserted", "balls");
                        }
                    } catch (Exception ex) {
                        Log.d("Getting all champs error: ", ex.toString());
                    }
                }
            });
        }

        // Check that MainActivity is using fragment_container layout
        if (findViewById(R.id.fragment_container) != null) {

            // If we're being restored from previous state, return.
            // This prevents overlapping fragments
            if (savedInstanceState != null)
                return;

            // Create a new MainFragment
            MainFragment mainFrag = new MainFragment();

            // Add mainFrag to 'fragment_container'
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFrag).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to interact with main fragment
    public void onButtonPressed(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // If image is pressed, replace main fragment with appropriate fragment
        if (view.getId() == R.id.caitlyn_button) {

            ScouterFragment scouterFragment = new ScouterFragment();
            transaction.replace(R.id.fragment_container, scouterFragment);

        } else if (view.getId() == R.id.velkoz_button) {

            ProfileFragment profileFragment = new ProfileFragment();
            transaction.replace(R.id.fragment_container, profileFragment);

        } else if (view.getId() == R.id.jinx_button) {

            ChampFragment champFragment = new ChampFragment();
            transaction.replace(R.id.fragment_container, champFragment);

        } else {
            // Something went wrong
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onChampClicked(String champ_name){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetailedChampFragment detailedChampFragment = new DetailedChampFragment();
        //System.out.println("Champion name = " + champ_name);
        // Pass the champion name to new fragment
        int champ_id = dbHelper.getId(champ_name);
        String champ_id_string = "" + champ_id;
        String[] champ_data = new String[2];
        champ_data[0] = champ_name;
        champ_data[1] = champ_id_string;
        //Log.d("Champion ID is this ", "" + champ_id);
        Bundle args = new Bundle();
        args.putStringArray("CHAMP_DATA",champ_data);
        //args.putInt("CHAMP_ID", champ_id);
        detailedChampFragment.setArguments(args);

        //Pass the champion name and ID to new fragment from table

        transaction.replace(R.id.fragment_container, detailedChampFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onScoutAction(int[] id_list)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ScoutProfileFragment fragment = new ScoutProfileFragment();
        Bundle args = new Bundle();
        args.putIntArray("ids",id_list);
        fragment.setArguments(args);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
