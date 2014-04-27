package com.lawl.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends ActionBarActivity implements MainFragment.OnButtonPressListener, ChampFragment.OnChampClickListener, ScouterFragment.OnScoutActionListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        System.out.println("Champion name = " + champ_name);
        // Pass the champion name to new fragment
        Bundle args = new Bundle();
        args.putString("CHAMP_NAME", champ_name);
        detailedChampFragment.setArguments(args);

        transaction.replace(R.id.fragment_container, detailedChampFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onScoutAction(String names)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ScoutProfileFragment fragment = new ScoutProfileFragment();
        Bundle args = new Bundle();
        args.putString("NAMES",names);
        fragment.setArguments(args);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}