package com.lawl.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoreChampFragment extends Fragment {


    public LoreChampFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_lore_champ, container, false);

        final String champ_name;
        int champ_id;
        String url;
        RiotApiClient client = new RiotApiClient("18a18101-dc93-45ff-8bb2-4180fabf6472");

        Bundle args = getArguments();

        if (args != null) {
            champ_name = args.getString("CHAMP_NAME");
            champ_id = args.getInt("CHAMP_ID");
            url = String.format("/api/lol/static-data/%s/v1.2/champion/%d?champData=lore&", "na", champ_id);

            client.get(url, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        String lore = response.getString("lore");
                        TextView loreTextView = (TextView) v.findViewById(R.id.lore);
                        String lore_with_newlines = lore.replace("<br>", "\n");
                        loreTextView.setText(lore_with_newlines);
                    } catch (Exception ex) {
                        Log.d("Getting lore error", ex.toString());
                    }
                }
            });


        }
        else champ_name = "error";
        return v;
    }


}
