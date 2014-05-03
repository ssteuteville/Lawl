package com.lawl.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

public class SkillChampFragment extends Fragment {


    public SkillChampFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_skill_champ, container, false);

        String champ_name;
        int champ_id;
        String url;
        RiotApiClient client = new RiotApiClient("18a18101-dc93-45ff-8bb2-4180fabf6472");


        // Retrieve champion name from our main activity
        TextView champTextView = (TextView) v.findViewById(R.id.champ_text);
        Bundle args = getArguments();

        if (args != null) {
            champ_name = args.getString("CHAMP_NAME");
            champ_id = args.getInt("CHAMP_ID");
            champTextView.setText(champ_name + " " + champ_id);

            url = String.format("/api/lol/static-data/%s/v1.2/champion/%d?champData=passive,spells&", "na", champ_id);

            client.get(url, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    try {
                        JSONArray spells = response.getJSONArray("spells");

                        String passive_name = response.getJSONObject("passive").get("name").toString();
                        String passive_desc = response.getJSONObject("passive").get("sanitizedDescription").toString();

                        String spell_names[] = new String[spells.length()];
                        String spell_desc[] = new String[spells.length()];

                        for(int i = 0; i < spells.length(); i++) {
                            spell_names[i] = spells.getJSONObject(i).get("name").toString();
                            spell_desc[i] = spells.getJSONObject(i).get("sanitizedDescription").toString();
                        }

                        TextView passiveName = (TextView) v.findViewById(R.id.skill_passive);
                        passiveName.setText("Passive: " + passive_name);
                        passiveName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        TextView passiveDesc = (TextView) v.findViewById(R.id.skill_passive_description);
                        passiveDesc.setText(passive_desc);

                        TextView skillOneName = (TextView) v.findViewById(R.id.skill1);
                        skillOneName.setText("Q: " + spell_names[0]);
                        skillOneName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        TextView skillOneDesc = (TextView) v.findViewById(R.id.skill1_description);
                        skillOneDesc.setText(spell_desc[0]);

                        TextView skillTwoName = (TextView) v.findViewById(R.id.skill2);
                        skillTwoName.setText("W: " + spell_names[1]);
                        skillTwoName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        TextView skillTwoDesc = (TextView) v.findViewById(R.id.skill2_description);
                        skillTwoDesc.setText(spell_desc[1]);

                        TextView skillThreeName = (TextView) v.findViewById(R.id.skill3);
                        skillThreeName.setText("E: " + spell_names[2]);
                        skillThreeName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        TextView skillThreeDesc = (TextView) v.findViewById(R.id.skill3_description);
                        skillThreeDesc.setText(spell_desc[2]);

                        TextView skillFourName = (TextView) v.findViewById(R.id.skill4);
                        skillFourName.setText("R: " + spell_names[3]);
                        skillFourName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                        TextView skillFourDesc = (TextView) v.findViewById(R.id.skill4_description);
                        skillFourDesc.setText(spell_desc[3]);

                    } catch (Exception ex) {
                        Log.d("Getting champ spells error", ex.toString());
                    }
                }
            });


        }
        else champ_name = "error";

        // Set splash image based on retrieved name
        ImageView champImageView = (ImageView) v.findViewById(R.id.champ_image);

        if (champ_name.equals("Aatrox")) {
            champImageView.setImageResource(R.drawable.aatrox_splash_0);
        }	else if (champ_name.equals("Ahri")) {
            champImageView.setImageResource(R.drawable.ahri_splash_0);
        }	else if (champ_name.equals("Akali")) {
            champImageView.setImageResource(R.drawable.akali_splash_0);
        }	else if (champ_name.equals("Alistar")) {
            champImageView.setImageResource(R.drawable.alistar_splash_0);
        }	else if (champ_name.equals("Amumu")) {
            champImageView.setImageResource(R.drawable.amumu_splash_0);
        }	else if (champ_name.equals("Anivia")) {
            champImageView.setImageResource(R.drawable.anivia_splash_0);
        }	else if (champ_name.equals("Annie")) {
            champImageView.setImageResource(R.drawable.annie_splash_0);
        }	else if (champ_name.equals("Ashe")) {
            champImageView.setImageResource(R.drawable.ashe_splash_0);
        }	else if (champ_name.equals("Blitzcrank")) {
            champImageView.setImageResource(R.drawable.blitzcrank_splash_0);
        }	else if (champ_name.equals("Brand")) {
            champImageView.setImageResource(R.drawable.brand_splash_0);
        }	else if (champ_name.equals("Caitlyn")) {
            champImageView.setImageResource(R.drawable.caitlyn_splash_0);
        }	else if (champ_name.equals("Cassiopeia")) {
            champImageView.setImageResource(R.drawable.cassiopeia_splash_0);
        }	else if (champ_name.equals("Chogath")) {
            champImageView.setImageResource(R.drawable.chogath_splash_0);
        }	else if (champ_name.equals("Corki")) {
            champImageView.setImageResource(R.drawable.corki_splash_0);
        }	else if (champ_name.equals("Darius")) {
            champImageView.setImageResource(R.drawable.darius_splash_0);
        }	else if (champ_name.equals("DrMundo")) {
            champImageView.setImageResource(R.drawable.drmundo_splash_0);
        }	else if (champ_name.equals("Elise")) {
            champImageView.setImageResource(R.drawable.elise_splash_0);
        }	else if (champ_name.equals("Evelynn")) {
            champImageView.setImageResource(R.drawable.evelynn_splash_0);
        }	else if (champ_name.equals("Ezreal")) {
            champImageView.setImageResource(R.drawable.ezreal_splash_0);
        }	else if (champ_name.equals("FiddleSticks")) {
            champImageView.setImageResource(R.drawable.fiddlesticks_splash_0);
        }	else if (champ_name.equals("Fiora")) {
            champImageView.setImageResource(R.drawable.fiora_splash_0);
        }	else if (champ_name.equals("Fizz")) {
            champImageView.setImageResource(R.drawable.fizz_splash_0);
        }	else if (champ_name.equals("Galio")) {
            champImageView.setImageResource(R.drawable.galio_splash_0);
        }	else if (champ_name.equals("Gangplank")) {
            champImageView.setImageResource(R.drawable.gangplank_splash_0);
        }	else if (champ_name.equals("Garen")) {
            champImageView.setImageResource(R.drawable.garen_splash_0);
        }	else if (champ_name.equals("Gragas")) {
            champImageView.setImageResource(R.drawable.gragas_splash_0);
        }	else if (champ_name.equals("Graves")) {
            champImageView.setImageResource(R.drawable.graves_splash_0);
        }	else if (champ_name.equals("Hecarim")) {
            champImageView.setImageResource(R.drawable.hecarim_splash_0);
        }	else if (champ_name.equals("Heimerdinger")) {
            champImageView.setImageResource(R.drawable.heimerdinger_splash_0);
        }	else if (champ_name.equals("Irelia")) {
            champImageView.setImageResource(R.drawable.irelia_splash_0);
        }	else if (champ_name.equals("Janna")) {
            champImageView.setImageResource(R.drawable.janna_splash_0);
        }	else if (champ_name.equals("JarvanIV")) {
            champImageView.setImageResource(R.drawable.jarvaniv_splash_0);
        }	else if (champ_name.equals("Jax")) {
            champImageView.setImageResource(R.drawable.jax_splash_0);
        }	else if (champ_name.equals("Jayce")) {
            champImageView.setImageResource(R.drawable.jayce_splash_0);
        }	else if (champ_name.equals("Jinx")) {
            champImageView.setImageResource(R.drawable.jinx_splash_0);
        }	else if (champ_name.equals("Karma")) {
            champImageView.setImageResource(R.drawable.karma_splash_0);
        }	else if (champ_name.equals("Karthus")) {
            champImageView.setImageResource(R.drawable.karthus_splash_0);
        }	else if (champ_name.equals("Kassadin")) {
            champImageView.setImageResource(R.drawable.kassadin_splash_0);
        }	else if (champ_name.equals("Katarina")) {
            champImageView.setImageResource(R.drawable.katarina_splash_0);
        }	else if (champ_name.equals("Kayle")) {
            champImageView.setImageResource(R.drawable.kayle_splash_0);
        }	else if (champ_name.equals("Kennen")) {
            champImageView.setImageResource(R.drawable.kennen_splash_0);
        }   else if (champ_name.equals("Khazix")) {
            champImageView.setImageResource(R.drawable.khazix_splash_0);
        }   else if (champ_name.equals("KogMaw")) {
            champImageView.setImageResource(R.drawable.kogmaw_splash_0);
        }	else if (champ_name.equals("Leblanc")) {
            champImageView.setImageResource(R.drawable.leblanc_splash_0);
        }	else if (champ_name.equals("LeeSin")) {
            champImageView.setImageResource(R.drawable.leesin_splash_0);
        }	else if (champ_name.equals("Leona")) {
            champImageView.setImageResource(R.drawable.leona_splash_0);
        }	else if (champ_name.equals("Lissandra")) {
            champImageView.setImageResource(R.drawable.lissandra_splash_0);
        }	else if (champ_name.equals("Lucian")) {
            champImageView.setImageResource(R.drawable.lucian_splash_0);
        }	else if (champ_name.equals("Lulu")) {
            champImageView.setImageResource(R.drawable.lulu_splash_0);
        }	else if (champ_name.equals("Lux")) {
            champImageView.setImageResource(R.drawable.lux_splash_0);
        }	else if (champ_name.equals("Malphite")) {
            champImageView.setImageResource(R.drawable.malphite_splash_0);
        }	else if (champ_name.equals("Malzahar")) {
            champImageView.setImageResource(R.drawable.malzahar_splash_0);
        }	else if (champ_name.equals("Maokai")) {
            champImageView.setImageResource(R.drawable.maokai_splash_0);
        }	else if (champ_name.equals("MasterYi")) {
            champImageView.setImageResource(R.drawable.masteryi_splash_0);
        }	else if (champ_name.equals("MissFortune")) {
            champImageView.setImageResource(R.drawable.missfortune_splash_0);
        }	else if (champ_name.equals("Mordekaiser")) {
            champImageView.setImageResource(R.drawable.mordekaiser_splash_0);
        }	else if (champ_name.equals("Morgana")) {
            champImageView.setImageResource(R.drawable.morgana_splash_0);
        }	else if (champ_name.equals("Nami")) {
            champImageView.setImageResource(R.drawable.nami_splash_0);
        }	else if (champ_name.equals("Nasus")) {
            champImageView.setImageResource(R.drawable.nasus_splash_0);
        }	else if (champ_name.equals("Nautilus")) {
            champImageView.setImageResource(R.drawable.nautilus_splash_0);
        }	else if (champ_name.equals("Nidalee")) {
            champImageView.setImageResource(R.drawable.nidalee_splash_0);
        }	else if (champ_name.equals("Nocturne")) {
            champImageView.setImageResource(R.drawable.nocturne_splash_0);
        }	else if (champ_name.equals("Nunu")) {
            champImageView.setImageResource(R.drawable.nunu_splash_0);
        }	else if (champ_name.equals("Olaf")) {
            champImageView.setImageResource(R.drawable.olaf_splash_0);
        }	else if (champ_name.equals("Orianna")) {
            champImageView.setImageResource(R.drawable.orianna_splash_0);
        }	else if (champ_name.equals("Pantheon")) {
            champImageView.setImageResource(R.drawable.pantheon_splash_0);
        }	else if (champ_name.equals("Poppy")) {
            champImageView.setImageResource(R.drawable.poppy_splash_0);
        }	else if (champ_name.equals("Quinn")) {
            champImageView.setImageResource(R.drawable.quinn_splash_0);
        }	else if (champ_name.equals("Rammus")) {
            champImageView.setImageResource(R.drawable.rammus_splash_0);
        }	else if (champ_name.equals("Renekton")) {
            champImageView.setImageResource(R.drawable.renekton_splash_0);
        }	else if (champ_name.equals("Rengar")) {
            champImageView.setImageResource(R.drawable.rengar_splash_0);
        }	else if (champ_name.equals("Riven")) {
            champImageView.setImageResource(R.drawable.riven_splash_0);
        }	else if (champ_name.equals("Rumble")) {
            champImageView.setImageResource(R.drawable.rumble_splash_0);
        }	else if (champ_name.equals("Ryze")) {
            champImageView.setImageResource(R.drawable.ryze_splash_0);
        }	else if (champ_name.equals("Sejuani")) {
            champImageView.setImageResource(R.drawable.sejuani_splash_0);
        }	else if (champ_name.equals("Shaco")) {
            champImageView.setImageResource(R.drawable.shaco_splash_0);
        }	else if (champ_name.equals("Shen")) {
            champImageView.setImageResource(R.drawable.shen_splash_0);
        }	else if (champ_name.equals("Shyvana")) {
            champImageView.setImageResource(R.drawable.shyvana_splash_0);
        }	else if (champ_name.equals("Singed")) {
            champImageView.setImageResource(R.drawable.singed_splash_0);
        }	else if (champ_name.equals("Sion")) {
            champImageView.setImageResource(R.drawable.sion_splash_0);
        }	else if (champ_name.equals("Sivir")) {
            champImageView.setImageResource(R.drawable.sivir_splash_0);
        }	else if (champ_name.equals("Skarner")) {
            champImageView.setImageResource(R.drawable.skarner_splash_0);
        }	else if (champ_name.equals("Sona")) {
            champImageView.setImageResource(R.drawable.sona_splash_0);
        }	else if (champ_name.equals("Soraka")) {
            champImageView.setImageResource(R.drawable.soraka_splash_0);
        }	else if (champ_name.equals("Swain")) {
            champImageView.setImageResource(R.drawable.swain_splash_0);
        }	else if (champ_name.equals("Talon")) {
            champImageView.setImageResource(R.drawable.talon_splash_0);
        }	else if (champ_name.equals("Taric")) {
            champImageView.setImageResource(R.drawable.taric_splash_0);
        }	else if (champ_name.equals("Teemo")) {
            champImageView.setImageResource(R.drawable.teemo_splash_0);
        }	else if (champ_name.equals("Thresh")) {
            champImageView.setImageResource(R.drawable.thresh_splash_0);
        }	else if (champ_name.equals("Tristana")) {
            champImageView.setImageResource(R.drawable.tristana_splash_0);
        }	else if (champ_name.equals("Trundle")) {
            champImageView.setImageResource(R.drawable.trundle_splash_0);
        }	else if (champ_name.equals("Tryndamere")) {
            champImageView.setImageResource(R.drawable.tryndamere_splash_0);
        }	else if (champ_name.equals("TwistedFate")) {
            champImageView.setImageResource(R.drawable.twistedfate_splash_0);
        }	else if (champ_name.equals("Twitch")) {
            champImageView.setImageResource(R.drawable.twitch_splash_0);
        }	else if (champ_name.equals("Udyr")) {
            champImageView.setImageResource(R.drawable.udyr_splash_0);
        }	else if (champ_name.equals("Urgot")) {
            champImageView.setImageResource(R.drawable.urgot_splash_0);
        }	else if (champ_name.equals("Varus")) {
            champImageView.setImageResource(R.drawable.varus_splash_0);
        }	else if (champ_name.equals("Vayne")) {
            champImageView.setImageResource(R.drawable.vayne_splash_0);
        }	else if (champ_name.equals("Veigar")) {
            champImageView.setImageResource(R.drawable.veigar_splash_0);
        }	else if (champ_name.equals("Velkoz")) {
            champImageView.setImageResource(R.drawable.velkoz_splash_0);
        }	else if (champ_name.equals("Vi")) {
            champImageView.setImageResource(R.drawable.vi_splash_0);
        }	else if (champ_name.equals("Viktor")) {
            champImageView.setImageResource(R.drawable.viktor_splash_0);
        }	else if (champ_name.equals("Vladimir")) {
            champImageView.setImageResource(R.drawable.vladimir_splash_0);
        }	else if (champ_name.equals("Volibear")) {
            champImageView.setImageResource(R.drawable.volibear_splash_0);
        }	else if (champ_name.equals("Warwick")) {
            champImageView.setImageResource(R.drawable.warwick_splash_0);
        }	else if (champ_name.equals("MonkeyKing")) {
            champImageView.setImageResource(R.drawable.wukong_splash_0);
        }	else if (champ_name.equals("Xerath")) {
            champImageView.setImageResource(R.drawable.xerath_splash_0);
        }	else if (champ_name.equals("XinZhao")) {
            champImageView.setImageResource(R.drawable.xinzhao_splash_0);
        }	else if (champ_name.equals("Yasuo")) {
            champImageView.setImageResource(R.drawable.yasuo_splash_0);
        }	else if (champ_name.equals("Yorick")) {
            champImageView.setImageResource(R.drawable.yorick_splash_0);
        }	else if (champ_name.equals("Zac")) {
            champImageView.setImageResource(R.drawable.zac_splash_0);
        }	else if (champ_name.equals("Zed")) {
            champImageView.setImageResource(R.drawable.zed_splash_0);
        }	else if (champ_name.equals("Ziggs")) {
            champImageView.setImageResource(R.drawable.ziggs_splash_0);
        }	else if (champ_name.equals("Zilean")) {
            champImageView.setImageResource(R.drawable.zilean_splash_0);
        }	else if (champ_name.equals("Zyra")) {
            champImageView.setImageResource(R.drawable.zyra_splash_0);
        }


        return v;
    }


}
