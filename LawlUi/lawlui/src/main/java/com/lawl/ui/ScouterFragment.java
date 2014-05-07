package com.lawl.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Config;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
            mListener = (OnScoutActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void handleText(final String names) {
        this.name_list.clear();
        this.name_list.addAll(Arrays.asList(names.split("\\s*,\\s*")));
        String url = String.format("/api/lol/%s/v1.4/summoner/by-name/%s?", "na", names);
        progressBar.setVisibility(View.VISIBLE);
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(final JSONObject response)
            {
                try {
                    final int[] ids = new int[name_list.size()];
                    final IntWrapper count = new IntWrapper(0);//since variables must be declared constant in order to be persisted into a handler we have to use an "IntWrapper"
                    for(int i = 0; i < name_list.size(); i++) //java foreach loop -> do stuff for every name in the list
                    {
                        ids[i]  = response.getJSONObject(name_list.get(i)).getInt("id");
                        Log.e("XXXXXXXXXXXXXXXXXX", Integer.toString(ids[i]));
                    }

                    mListener.onScoutAction(ids);
                }
                catch(JSONException ex)
                {
                    textView.setText(name_list.toString());
                }

            }
        });



    }
    public interface OnScoutActionListener {
        public void onScoutAction(int[] id_list);
    }


}

class IntWrapper {
    public IntWrapper(int i)
    {
        this.integer = i;
    }
    public int integer;
}
