package com.lawl.ui;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ProfileFragment extends Fragment {

    RiotApiClient client = new RiotApiClient("0b63c21d-b03a-4c25-b481-57d853f29a08");
    int id;
    TextView textViewName;
    TextView textViewMasteries;
    TextView textViewRank;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    View view;
    LinearLayout layout;
    ProgressBar progressBar;


    //OnProfileActionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.id = getArguments().getInt("id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        //layout = (LinearLayout) view.findViewById(R.id.profile_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        //Name
        textViewName = (TextView) view.findViewById(R.id.name);
        getName(this.id);

        //Rank
        /*textView = (TextView) view.findViewById(R.id.current_rank);
        getRank(this.id);*/

        //Masteries
        textViewMasteries = (TextView) view.findViewById(R.id.masteries);
        getMasteries(this.id);

        //Ranked and Normal Wins (Ranked wins and losses, normal wins)
        textView1 = (TextView) view.findViewById(R.id.ranked_wins);
        textView2 = (TextView) view.findViewById(R.id.ranked_losses);
        textView3 = (TextView) view.findViewById(R.id.normal_wins);
        getWins(this.id);

        //Ranked and Normal Losses

        //Runes
        /*textView = (TextView) view.findViewById(R.id.runes);
        getRunes(this.id);*/


        //Return
        return view;
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //
            mListener = (OnProfileActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void getRank(int summoner_id) {
        /*String url = '/api/lol/na/v1.4/summoner/%d/masteries?';
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    JSONObject champs = response.getJSONObject("data");
                    JSONArray names = champs.names();
                    }
                } catch (Exception ex) {
                    Log.d("Getting all champs error: ", ex.toString());
                }
            }
        });*/
    }

    public void getWins(int summoner_id) {
        String url = String.format("/api/lol/na/v1.3/stats/by-summoner/%d/summary?", summoner_id);
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    //5 is Ranked Solo 5x5, 8 is Unranked
                    JSONArray gameTypes = response.getJSONArray("playerStatSummaries");
                    JSONObject rankSolo = gameTypes.getJSONObject(5);
                    textView2.setText("Ranked Losses: " + rankSolo.getString("losses"));
                    textView1.setText("Ranked Wins: " + rankSolo.getString("wins"));
                    JSONObject unranked = gameTypes.getJSONObject(8);
                    textView3.setText("Normal Wins: " + unranked.getString("wins"));
                }
                catch (JSONException ex) {
                    Log.e("Profile Fragment", "Failure Get Wins: " + ex);
                }
            }
        });

    }

    public void getMasteries(int summoner_id) {
        //final Masteries masteries;
        String url = String.format("/api/lol/na/v1.4/summoner/%d/masteries?", summoner_id);
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    Masteries masteries;
                    int mastery_pages[] = new int[3];
                    for(int i=0; i < response.length(); i++) {
                        JSONObject resp = response.getJSONObject(response.names().getString(i));
                        JSONArray pages = resp.getJSONArray("pages");
                        for(int k=0; k < pages.length(); k++) {
                            JSONObject page = pages.getJSONObject(k);
                            if (page.getBoolean("current")) {
                                JSONArray _masteries = page.getJSONArray("masteries");
                                for (int j = 0; j < _masteries.length(); j++) {
                                    JSONObject mastery = _masteries.getJSONObject(j);
                                    int mastery_num = Character.getNumericValue(mastery.getString("id").charAt(1));
                                    mastery_pages[mastery_num - 1] += mastery.getInt("rank");
                                }
                                k = pages.length();
                            }
                        }
                    }
                    masteries = new Masteries(mastery_pages[0], mastery_pages[1], mastery_pages[2]);
                    textViewMasteries.setText("Masteries: " + masteries.toString());
            }
                catch (JSONException ex) {
                    Log.e("ProfileFragment", "Failure on masteries: " + ex);
                }
        }
    });
    }

    public void getRunes(int summoner_id) {


    }

    public void getName(int summoner_id) {
        String url = String.format("/api/lol/na/v1.4/summoner/%d/name?", summoner_id);
        final String id = summoner_id + "";
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    String name;
                    name = response.getString(id);
                    Log.d("ProfileFragment", "Getting name: " + name);
                    textViewName.setText(name);
                }
                catch (JSONException ex) {
                    Log.e("ProfileFragment", "Failure on Name: " + ex);
                }
            }
        });
    }

}
