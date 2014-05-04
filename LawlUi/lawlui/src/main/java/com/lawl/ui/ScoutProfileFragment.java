package com.lawl.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.google.gson.JsonArray;
import com.lawl.ui.dummy.DummyContent;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
        profiles = new ScoutProfile[3]; //index shouldn't be hard coded use ids.length
        profiles[0] = new ScoutProfile("Wizard of Sawz", "Silver", "Silver", "21/9/0", 40);
        profiles[1] = new ScoutProfile("Diamonz", "Silver", "Silver", "0/21/9", 76);
        profiles[2] = new ScoutProfile("SAVAGENEDVED", "Silver", "Silver", "0/30/0", 20);
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

    public void setAdapter(View view)
    {
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
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
            StringBuilder retBuilder = new StringBuilder();
            retBuilder.append("{ \"responses\":[  ");
            for(int i = 0; i < ids[0].length; i++)
            {
                String url = String.format("/api/lol/na/v2.3/league/by-summoner/%d/entry?", ids[0][i]);
                HttpClient profileClient = new DefaultHttpClient();
                try{
                    HttpGet request = new HttpGet(BASE_URL + url + API_KEY);
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
                        Log.e("GetProfiles", "Failed request with id " + ids[0][i] + " Status code: " + status.getStatusCode() + "URL: " + BASE_URL + url + API_KEY );
                    }


                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            retBuilder.append("]}");
            Log.d("GetProfiles", "About to return the following string:\n" + retBuilder.toString());
            return retBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result)
        {
            ScoutProfile[] profiles = new ScoutProfile[ids.length];
            try
            {
                JSONObject json = new JSONObject(result);
                JSONArray jsonArray = json.getJSONArray("responses");
                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONArray response = jsonArray.getJSONArray(i);
                    Log.d("GetProfiles", "Response " + i + ": " + response.toString());
                    for(int j = 0; j < response.length(); j++)
                    {
                        JSONObject match_type = response.getJSONObject(j);
                        Log.d("GetProfiles", "match_type " + j + ": " + match_type.toString());
                        if(match_type.get("queueType") == "RANKED_SOLO_5x5")
                        {
                            profiles[i] = new ScoutProfile(match_type.getString("playerOrTeamName"), "Silver", "Silver", "n/a", 40);
                            Log.d("GetProfiles", "profile" + i + ": " + profiles[i].getName());
                            j = response.length();
                        }
                    }
                    Log.d("GetProfiles", "Parse completed: " + profiles[i].getName());
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
