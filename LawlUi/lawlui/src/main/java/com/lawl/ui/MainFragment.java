package com.lawl.ui;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/*
 * MainFragment is the initial menu page.
 * Three buttons are displayed, SCOUT, PROFILE, and CHAMPS
 * onButtonPressed interface calls MainActivity to replace with appropriate fragment.
 */

public class MainFragment extends Fragment {

    OnButtonPressListener mCallback;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView scout_button = (ImageView) v.findViewById(R.id.caitlyn_button);
        ImageView profile_button = (ImageView) v.findViewById(R.id.velkoz_button);
        ImageView champs_button = (ImageView) v.findViewById(R.id.jinx_button);

        scout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open scout fragment
                mCallback.onButtonPressed(view.findViewById(R.id.caitlyn_button));
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open profile fragment
                mCallback.onButtonPressed(view.findViewById(R.id.velkoz_button));

            }
        });

        champs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open champion fragment
                mCallback.onButtonPressed(view.findViewById(R.id.jinx_button));
            }
        });



        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnButtonPressListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnButtonPressedListener");
        }

    }

    public interface OnButtonPressListener {
        public void onButtonPressed(View view);
    }

}
