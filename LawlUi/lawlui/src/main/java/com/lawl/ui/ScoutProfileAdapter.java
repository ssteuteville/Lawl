package com.lawl.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shane Steuteville on 4/26/2014.
 */
public class ScoutProfileAdapter extends BaseAdapter {
    private Context context;
    String[] names;

    public ScoutProfileAdapter(Context context, String[] names)
    {
        this.names = names;
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.names.length;
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

        TextView tv = (TextView) scoutProfileView.findViewById(R.id.ScoutProfileTextView);
        tv.setText(names[i]);
        return scoutProfileView;
    }
}
