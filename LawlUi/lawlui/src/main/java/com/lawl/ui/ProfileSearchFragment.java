package com.lawl.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Garreth on 5/4/2014.
 */
public class ProfileSearchFragment extends Fragment {


    EditText editText;
    TextView textView;
    String summoner_name;
    ProgressBar progressBar;
    ListView listView;
    RiotApiClient client;
    //final List<String> name_list = new ArrayList<String>();
    OnProfileSearchActionListener mListener;
    int summoner_id;

    public ProfileSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_profilesearch, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        textView = (TextView) v.findViewById(R.id.profile_search_text);

        editText = (EditText) v.findViewById(R.id.profile_search_edit_text);
        editText.setHint("Please enter summoner name(s)");

        client = new RiotApiClient("0b63c21d-b03a-4c25-b481-57d853f29a08");

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    summoner_name = editText.getText().toString();
                    handleText(summoner_name);
                    handled = true;
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
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
            mListener = (OnProfileSearchActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void handleText(final String name) {
        //this.name_list.addAll(Arrays.asList(names.split("\\s*,\\s*")));
        String url = String.format("/api/lol/%s/v1.4/summoner/by-name/%s?", "na", name);
        progressBar.setVisibility(View.VISIBLE);
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(final JSONObject response)
            {
                try {
                    summoner_id = response.getJSONObject(name).getInt("id");
                    Log.d("ProfileSearchFragment ", "Success for " + name);

                    mListener.onProfileSearchAction(summoner_id);
                }
                catch(JSONException ex)
                {
                    textView.setText(summoner_name);
                    Log.e("ProfileSearchFragment ", "Failure for " + name);

                    //textView.setText(name_list.toString());

                }

            }
        });



    }
    public interface OnProfileSearchActionListener {
        public void onProfileSearchAction(int summoner_id);
    }




}
