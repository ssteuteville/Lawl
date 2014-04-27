package com.lawl.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ScouterFragment extends Fragment {

    EditText editText;
    TextView textView;
    String summoner_names;
    ProgressBar progressBar;
    ListView listView;
    RiotApiClient client;
    final List<String> name_list = new ArrayList<String>();
    OnScoutActionListener mListener;

    public ScouterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_scouter, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        textView = (TextView) v.findViewById(R.id.scouter_text);

        editText = (EditText) v.findViewById(R.id.scouter_edit_text);
        editText.setHint("Please enter summoner name(s)");

        client = new RiotApiClient("0b63c21d-b03a-4c25-b481-57d853f29a08");

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    summoner_names = editText.getText().toString();
                    handleText(summoner_names);
                    handled = true;
                }
                return handled;
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnScoutActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void handleText(String names) {
        this.name_list.addAll(Arrays.asList(names.split("\\s*,\\s*")));
        //String url = String.format("/api/lol/%s/v1.4/summoner/by-name/%s?", "na", names);
        progressBar.setVisibility(View.VISIBLE);
        mListener.onScoutAction(names);
//        client.get(url, null, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(final JSONObject response)
//            {
//                try {
//                    final HashMap<String, String> ids = new HashMap<String,String>();//just testing out how hashmaps work in java and providing an example of persisting an object into the handler below. aka making it final
//                    final IntWrapper count = new IntWrapper(0);//since variables must be declared constant in order to be persisted into a handler we have to use an "IntWrapper"
//                    for(final String name : name_list) //java foreach loop -> do stuff for every name in the list
//                    {
//                        String id = response.getJSONObject(name).get("id").toString(); //get the id associated with a name
//                        ids.put(name, id); //add the id - name pair to hashmap
//                        final String id_url = String.format("/api/lol/na/v1.3/stats/by-summoner/%s/summary?season=SEASON4&", id); //format url properly. note: all urls will either have ? or & at the end. API key is inserted by client.
//                        Log.e("XXXXXXXXXXXXXXXXXX", id_url); //debug statement
//                        client.get(id_url, null, new JsonHttpResponseHandler(){ //make a new request for each name in the list
//                            @Override
//                            public void onSuccess(JSONObject id_response)
//                            {
//                                    //textView.append( name + "'s id is: " + ids.get(name) + " Here is their data: \n\n" +  id_response.toString() + "\n\n");//make ui changes -- here we could update a final List<ScoutProfile>
//                                    count.integer = count.integer + 1;
//                                    if(count.integer == name_list.size())
//                                    {
//                                        progressBar.setVisibility(View.GONE);
//                                        mListener.onScoutAction((String[]) name_list.toArray(new String[0]));
//                                    }
//                            }
//                        });
//                    }
//                }
//                catch(JSONException ex)
//                {
//                    textView.setText(name_list.toString());
//                }
//
//            }
//        });



    }
    public interface OnScoutActionListener {
        public void onScoutAction(String names);
    }


}

class IntWrapper {
    public IntWrapper(int i)
    {
        this.integer = i;
    }
    public int integer;
}
