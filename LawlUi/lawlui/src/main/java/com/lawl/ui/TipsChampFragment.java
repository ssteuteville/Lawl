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

public class TipsChampFragment extends Fragment {


    public TipsChampFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_tips_champ, container, false);

        final String champ_name;
        int champ_id;
        String url;
        RiotApiClient client = new RiotApiClient("18a18101-dc93-45ff-8bb2-4180fabf6472");

        Bundle args = getArguments();

        if (args != null) {
            champ_name = args.getString("CHAMP_NAME");
            champ_id = args.getInt("CHAMP_ID");
            url = String.format("/api/lol/static-data/%s/v1.2/champion/%d?champData=allytips,enemytips&", "na", champ_id);

            client.get(url, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONArray ally_tips = response.getJSONArray("allytips");
                        JSONArray enemy_tips = response.getJSONArray("enemytips");

                        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.tips_linear_layout);

                        final TextView[] allyTipsTextViews = new TextView[ally_tips.length()];
                        final TextView[] enemyTipsTextViews = new TextView[enemy_tips.length()];

                        final TextView allyTipsTitle = new TextView(getActivity());
                        allyTipsTitle.setText("Tips for playing as " + champ_name + ":");
                        allyTipsTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        linearLayout.addView(allyTipsTitle);

                        // Converts 20 dp to pixels for given screen
                        // This pixel value is then used to progrematically add padding
                        int padding_in_dp = 20;
                        final float scale = getResources().getDisplayMetrics().density;
                        int padding_in_px = (int) (padding_in_dp * scale + 0.5f);

                        for(int i = 0; i < ally_tips.length(); i++){
                            final TextView allyTipView = new TextView(getActivity());
                            allyTipView.setText("- " + ally_tips.getString(i));
                            allyTipView.setPadding(0, 0, 0, padding_in_px);
                            linearLayout.addView(allyTipView);
                            allyTipsTextViews[i] = allyTipView;
                        }

                        final TextView enemyTipsTitle = new TextView(getActivity());
                        enemyTipsTitle.setText("Tips for playing against " + champ_name + ":");
                        enemyTipsTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        enemyTipsTitle.setPadding(0, padding_in_px, 0, 0);
                        linearLayout.addView(enemyTipsTitle);

                        for(int i = 0; i < enemy_tips.length(); i++){
                            final TextView enemyTipView = new TextView(getActivity());
                            enemyTipView.setText("- " + enemy_tips.getString(i));
                            enemyTipView.setPadding(0, 0, 0, padding_in_px);
                            linearLayout.addView(enemyTipView);
                            enemyTipsTextViews[i] = enemyTipView;
                        }




                    } catch (Exception ex) {
                        Log.d("Getting tips error", ex.toString());
                    }
                }
            });


        }
        else champ_name = "error";

        return v;
    }


}
