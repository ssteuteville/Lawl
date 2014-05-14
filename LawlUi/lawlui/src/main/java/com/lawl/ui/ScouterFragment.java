package com.lawl.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ScouterFragment extends Fragment {

    TextView textView;
    String summoner_names;
    ProgressBar progressBar;
    ListView listView;
    RiotApiClient client;
    List<String> name_list = new ArrayList<String>();
    OnScoutActionListener mListener;
    ArrayList<EditText> editTexts;
    LinearLayout layout;
    Button submit;
    View view;
    AlertDialog alertDialog;

    public ScouterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scouter, container, false);
        layout = (LinearLayout) view.findViewById(R.id.scouter_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        editTexts = new ArrayList<EditText>();
        submit = (Button)view.findViewById(R.id.ScouterSubmit);
        textView = (TextView) view.findViewById(R.id.scouter_text);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
        dialogBuilder.setTitle("Failed to retrieve information.");
        dialogBuilder.setMessage("One or more of the summoner names provided was invalid.")
                .setCancelable(false).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
        alertDialog = dialogBuilder.create();

        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleText();
            }
        });

        ImageButton addFieldButton = (ImageButton)view.findViewById(R.id.add_field_button);
        addFieldButton.setOnClickListener( new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                EditText eText = new EditText(v.getContext());
                eText.setHint("Enter a summoner name.");
                eText.setPadding(0,0,0,10);
                editTexts.add(eText);
                layout.addView(eText);
            }
        });

        client = new RiotApiClient("0b63c21d-b03a-4c25-b481-57d853f29a08");
        return view;
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

    private void handleText() {
        this.name_list.clear();
        String names = "";
        for(EditText eText : editTexts)
        {
            String curName = eText.getText().toString();
            if(curName != null && !curName.isEmpty())
            {
                curName = curName.toLowerCase();
                curName = curName.replace(" ", "");//these two lines are making the names adhere to riot's scheme
                this.name_list.add(curName);
                names += eText.getText().toString() + ",";
            }
        }
        String url = String.format("/api/lol/%s/v1.4/summoner/by-name/%s?", "na", names);
        progressBar.setVisibility(View.VISIBLE);
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(final JSONObject response)
            {
                try {
                    final int[] ids = new int[name_list.size()];
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
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                progressBar.setVisibility(View.GONE);
                alertDialog.show();
                error.printStackTrace();

            }
        });



    }
    public interface OnScoutActionListener {
        public void onScoutAction(int[] id_list);
    }


}

