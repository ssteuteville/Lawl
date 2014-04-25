package com.lawl.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScouterFragment extends Fragment {

    EditText editText;
    TextView textView;
    String summoner_names;
    ProgressBar progressBar;

    public ScouterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scouter, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        textView = (TextView) v.findViewById(R.id.scouter_text);

        editText = (EditText) v.findViewById(R.id.scouter_edit_text);
        editText.setHint("Please enter summoner name(s)");

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

    private void handleText(String string) {

        textView.setText(string);
        progressBar.setVisibility(View.VISIBLE);
        editText.setVisibility(View.INVISIBLE);



    }


}
