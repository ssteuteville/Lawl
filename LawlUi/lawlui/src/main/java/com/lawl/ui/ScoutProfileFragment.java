package com.lawl.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class ScoutProfileFragment extends ListFragment /*implements AbsListView.OnItemClickListener*/ {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int[] ids; //this should really be ScoutProfile[]
    private ScoutProfile[] profiles;

    //private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ScoutProfileAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static ScoutProfileFragment newInstance(String param1, String param2) {
        ScoutProfileFragment fragment = new ScoutProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScoutProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.ids = getArguments().getIntArray("ids");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoutprofile_list, container, false);
        new GetProfiles().execute(ids);

//        RiotApiClient client = new RiotApiClient("0b63c21d-b03a-4c25-b481-57d853f29a08");
        profiles = new ScoutProfile[ids.length];
        for(int i = 0; i < ids.length; i++)
        {
            profiles[i] = new ScoutProfile("DUMMY123456", "", "", "", 0);
        }
        mAdapter = new ScoutProfileAdapter(view.getContext(), profiles);

        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }
//    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(String id);
//    }

    private class GetProfiles extends AsyncTask<int[],Void, String>
    {
        String BASE_URL = "https://prod.api.pvp.net";
        String API_KEY = "api_key=0b63c21d-b03a-4c25-b481-57d853f29a08";

        @Override
        protected  String doInBackground(int[]... ids)
        {
            HttpClient profileClient = new DefaultHttpClient();
            StringBuilder retBuilder = new StringBuilder();
            retBuilder.append("{ \"current_season\":[  ");
            for(int i = 0; i < ids[0].length; i++)
            {
                String CURRENT_STATS_URL = String.format("/api/lol/na/v2.3/league/by-summoner/%d/entry?", ids[0][i]);
                try{
                    HttpGet request = new HttpGet(BASE_URL + CURRENT_STATS_URL + API_KEY);
                    HttpResponse response = profileClient.execute(request);
                    StatusLine status = response.getStatusLine();
                    if(status.getStatusCode() == 200)
                    {
                        HttpEntity entity = response.getEntity();
                        InputStream content = entity.getContent();
                        InputStreamReader streamReader = new InputStreamReader(content);
                        BufferedReader reader = new BufferedReader(streamReader);
                        String input;
                        while((input = reader.readLine()) != null)
                        {
                            retBuilder.append(input);
                        }
                        if(i != ids[0].length - 1)
                        {
                            retBuilder.append(",");
                        }
                        Log.d("GetProfiles", "Successful request with id " + ids[0][i] );
                    }
                    else
                    {
                        retBuilder.append("[ { \"playerOrTeamName\":\"Player Not Ranked\", \"queueType\":\"SOLO\", \"tier\":\"\", \"wins\":0 } ]");
                        if(i != ids[0].length - 1)
                        {
                            retBuilder.append(",");
                        }
                        Log.e("GetProfiles", "Failed request with id " + ids[0][i] + " Status code: " + status.getStatusCode() + "URL: " + BASE_URL + CURRENT_STATS_URL + API_KEY );
                    }


                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            retBuilder.append("], \"masteries\": [");

            for(int i = 0; i < ids[0].length; i++)
            {

                String MASTERIES_URL = String.format("/api/lol/na/v1.4/summoner/%d/masteries?", ids[0][i]);
                try {
                    HttpGet request = new HttpGet(BASE_URL + MASTERIES_URL + API_KEY);
                    HttpResponse response = profileClient.execute(request);
                    StatusLine status = response.getStatusLine();
                    if (status.getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream content = entity.getContent();
                        InputStreamReader streamReader = new InputStreamReader(content);
                        BufferedReader reader = new BufferedReader(streamReader);
                        String input;
                        while ((input = reader.readLine()) != null) {
                            retBuilder.append(input);
                        }
                        if (i != ids[0].length - 1) {
                            retBuilder.append(",");
                        }
                        Log.d("GetProfiles", "Successful request with id " + ids[0][i]);
                    }
                }
                catch (Exception ex)
                {

                }
            }
            retBuilder.append("]}");
            Log.d("GetProfiles", "About to return the following string:\n" + retBuilder.toString());
            return retBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result)
        {
            ScoutProfile[] results = new ScoutProfile[ids.length];
            CurrentSeason[] cur_season;
            Masteries[] masteries;
            try
            {
                JSONObject json = new JSONObject(result);
                cur_season = ParseCurrentSeason(json.getJSONArray("current_season"));
                masteries = ParseMasteries(json.getJSONArray("masteries"));
            }
            catch (Exception e)
            {
                cur_season = new CurrentSeason[ids.length];
                masteries = new Masteries[ids.length];
                e.printStackTrace();
            }
            for(int i = 0; i < cur_season.length; i++)
            {
                results[i] = new ScoutProfile(cur_season[i], masteries[i], "");
            }
            profiles = results;
            mAdapter.swapItems(profiles);
        }

        CurrentSeason[] ParseCurrentSeason(JSONArray input)
        {
            HashMap<String, Integer> Ranks = new HashMap<String, Integer>();
                Ranks.put("", 0);
                Ranks.put("BRONZE", 1);
                Ranks.put("SILVER", 2);
                Ranks.put("GOLD", 3);
                Ranks.put("PLATINUM", 4);
                Ranks.put("DIAMOND", 5);

            CurrentSeason[] results = new CurrentSeason[input.length()];
            try{
                for(int i = 0; i < input.length(); i++)
                {
                    JSONArray response = input.getJSONArray(i);     //array of match types
                    String curRank = "";     //keep track of highest rank as we check each match type
                    String name = "";     //get the players name from a solo match type
                    int wins = 0;     //sum of all wins
                    Log.d("GetProfiles", "Response " + i + ": " + response.toString());

                    for(int j = 0; j < response.length(); j++)
                    {
                        JSONObject match_type = response.getJSONObject(j);     //get a match type
                        if(name.equals("") && match_type.getString("queueType").contains("SOLO") )     //if name isn't initialized and the match type is solo
                            name = match_type.getString("playerOrTeamName");     //set name

                        String tier = match_type.getString("tier");    //get the rank of current match type
                        if(Ranks.get(tier) > Ranks.get(curRank))    //check if this match types rank is higher than the max rank so far
                            curRank = tier;     //set curRank

                        wins += match_type.getInt("wins");    //sum up wins for all match types

                    }
                    results[i] = new CurrentSeason(name, curRank, wins);
                    Log.d("GetProfiles", "Parse completed: " + results[i].name);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return results;
        }

        Masteries[] ParseMasteries(JSONArray input)
        {
            Masteries[] results = new Masteries[input.length()];
            try
            {
                for(int i = 0; i < input.length(); i++)
                {
                    JSONObject response = input.getJSONObject(i);
                    response = response.getJSONObject(response.names().getString(0));
                    JSONArray pages = response.getJSONArray("pages");
                    int mastery_pages[] = new int[3];
                    for(int j = 0; j < pages.length(); j++)
                    {
                        JSONObject page = pages.getJSONObject(j);
                        if(page.getBoolean("current"))
                        {
                            JSONArray masteries = page.getJSONArray("masteries");
                            for(int k = 0; k < masteries.length(); k++)
                            {
                                JSONObject mastery = masteries.getJSONObject(k);
                                String id = mastery.getString("id");
                                int mastery_num = Character.getNumericValue(mastery.getString("id").charAt(1));
                                mastery_pages[mastery_num - 1] += mastery.getInt("rank");
                            }
                            j = pages.length();
                        }
                    }
                    results[i] = new Masteries(mastery_pages[0], mastery_pages[1], mastery_pages[2]);

                }
            }
            catch(Exception ex)
            {

            }
            return results;
        }
    }

}
