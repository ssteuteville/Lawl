package com.lawl.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shane Steuteville on 4/26/2014.
 */
public class ScoutProfileAdapter extends BaseAdapter {
    private Context context;
    ScoutProfile[] profiles;

    public ScoutProfileAdapter(Context context, ScoutProfile[] profiles)
    {
        this.profiles = profiles;
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.profiles.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View scoutProfileView;
        if(view == null)
            scoutProfileView = inflater.inflate(R.layout.scout_profile, null);
        else
            scoutProfileView = view;

        final TextView nameView = (TextView) scoutProfileView.findViewById(R.id.ScoutProfileName);
        final TextView rankedWinsView = (TextView) scoutProfileView.findViewById(R.id.ScoutProfileWins);
        final ImageView previousRankView = (ImageView) scoutProfileView.findViewById(R.id.ScoutProfilePreviousRankImage);
        final ImageView currentRankView = (ImageView) scoutProfileView.findViewById(R.id.ScoutProfileCurrentRankImage);
        final TextView masteryInfoView = (TextView) scoutProfileView.findViewById(R.id.ScoutProfileMasteryInfo);
        Log.e("SSSSS", profiles[i].getName() );
        nameView.setText(profiles[i].getName());
        rankedWinsView.setText(Integer.toString(profiles[i].getRankedWins()));

        if(profiles[i].getPreviousRank() == "Silver")
            previousRankView.setImageResource(R.drawable.silver_badge);
        if(profiles[i].getCurrentRank() == "Silver")
            currentRankView.setImageResource(R.drawable.silver_badge);

        masteryInfoView.setText(profiles[i].getMasteryInfo());
        return scoutProfileView;
    }

   public void swapItems(ScoutProfile[] profiles)
   {
       this.profiles = profiles;
       notifyDataSetChanged();
   }
}
