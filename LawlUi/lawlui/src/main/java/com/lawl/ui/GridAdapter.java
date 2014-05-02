package com.lawl.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * GridAdapter defines each individual grid element.
 * It takes the name passed from ChampFragment and sets the ImageView and TextView accordingly.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private String[] champNames;

    public GridAdapter(Context context, String[] champNames) {
        this.context = context;
        this.champNames = champNames;
    }

    // getView() is an inherited Adapter method that gets the view at a specified position in the data set
    // We are using getView() to assign each individual grid element an image and a text field and then returning the entire gridView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        // convertView is not null whenever the view is changed (scrolling, orientation change, etc.)
        // So this sets our view and changes it whenever it needs to
        if (convertView == null)
        {
            // Get layout from grid_element.xml
            gridView = inflater.inflate(R.layout.grid_element, null);

        } else {
            gridView = convertView;
        }

        // Set text into textview based on the grid position
        TextView textView = (TextView) gridView.findViewById(R.id.grid_element_text);
        textView.setText(champNames[position]);

        // Set image based on selected text
        ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_element_image);

        String name = champNames[position];

        if (name.equals("Aatrox")) {
            imageView.setImageResource(R.drawable.aatrox_square_0);
        }	else if (name.equals("Ahri")) {
            imageView.setImageResource(R.drawable.ahri_square_0);
        }	else if (name.equals("Akali")) {
            imageView.setImageResource(R.drawable.akali_square_0);
        }	else if (name.equals("Alistar")) {
            imageView.setImageResource(R.drawable.alistar_square_0);
        }	else if (name.equals("Amumu")) {
            imageView.setImageResource(R.drawable.amumu_square_0);
        }	else if (name.equals("Anivia")) {
            imageView.setImageResource(R.drawable.anivia_square_0);
        }	else if (name.equals("Annie")) {
            imageView.setImageResource(R.drawable.annie_square_0);
        }	else if (name.equals("Ashe")) {
            imageView.setImageResource(R.drawable.ashe_square_0);
        }	else if (name.equals("Blitzcrank")) {
            imageView.setImageResource(R.drawable.blitzcrank_square_0);
        }	else if (name.equals("Brand")) {
            imageView.setImageResource(R.drawable.brand_square_0);
        }	else if (name.equals("Caitlyn")) {
            imageView.setImageResource(R.drawable.caitlyn_square_0);
        }	else if (name.equals("Cassiopeia")) {
            imageView.setImageResource(R.drawable.cassiopeia_square_0);
        }	else if (name.equals("Cho'gath")) {
            imageView.setImageResource(R.drawable.chogath_square_0);
        }	else if (name.equals("Corki")) {
            imageView.setImageResource(R.drawable.corki_square_0);
        }	else if (name.equals("Darius")) {
            imageView.setImageResource(R.drawable.darius_square_0);
        }	else if (name.equals("Dr. Mundo")) {
            imageView.setImageResource(R.drawable.drmundo_square_0);
        }	else if (name.equals("Elise")) {
            imageView.setImageResource(R.drawable.elise_square_0);
        }	else if (name.equals("Evelynn")) {
            imageView.setImageResource(R.drawable.evelynn_square_0);
        }	else if (name.equals("Ezreal")) {
            imageView.setImageResource(R.drawable.ezreal_square_0);
        }	else if (name.equals("Fiddlesticks")) {
            imageView.setImageResource(R.drawable.fiddlesticks_square_0);
        }	else if (name.equals("Fiora")) {
            imageView.setImageResource(R.drawable.fiora_square_0);
        }	else if (name.equals("Fizz")) {
            imageView.setImageResource(R.drawable.fizz_square_0);
        }	else if (name.equals("Galio")) {
            imageView.setImageResource(R.drawable.galio_square_0);
        }	else if (name.equals("Gangplank")) {
            imageView.setImageResource(R.drawable.gangplank_square_0);
        }	else if (name.equals("Garen")) {
            imageView.setImageResource(R.drawable.garen_square_0);
        }	else if (name.equals("Gragas")) {
            imageView.setImageResource(R.drawable.gragas_square_0);
        }	else if (name.equals("Graves")) {
            imageView.setImageResource(R.drawable.graves_square_0);
        }	else if (name.equals("Hecarim")) {
            imageView.setImageResource(R.drawable.hecarim_square_0);
        }	else if (name.equals("Heimerdinger")) {
            imageView.setImageResource(R.drawable.heimerdinger_square_0);
        }	else if (name.equals("Irelia")) {
            imageView.setImageResource(R.drawable.irelia_square_0);
        }	else if (name.equals("Janna")) {
            imageView.setImageResource(R.drawable.janna_square_0);
        }	else if (name.equals("Jarvan IV")) {
            imageView.setImageResource(R.drawable.jarvaniv_square_0);
        }	else if (name.equals("Jax")) {
            imageView.setImageResource(R.drawable.jax_square_0);
        }	else if (name.equals("Jayce")) {
            imageView.setImageResource(R.drawable.jayce_square_0);
        }	else if (name.equals("Jinx")) {
            imageView.setImageResource(R.drawable.jinx_square_0);
        }	else if (name.equals("Karma")) {
            imageView.setImageResource(R.drawable.karma_square_0);
        }	else if (name.equals("Karthus")) {
            imageView.setImageResource(R.drawable.karthus_square_0);
        }	else if (name.equals("Kassadin")) {
            imageView.setImageResource(R.drawable.kassadin_square_0);
        }	else if (name.equals("Katarina")) {
            imageView.setImageResource(R.drawable.katarina_square_0);
        }	else if (name.equals("Kayle")) {
            imageView.setImageResource(R.drawable.kayle_square_0);
        }	else if (name.equals("Kennen")) {
            imageView.setImageResource(R.drawable.kennen_square_0);
        }	else if (name.equals("Kha'zix")) {
            imageView.setImageResource(R.drawable.khazix_square_0);
        }	else if (name.equals("Kog'Maw")) {
            imageView.setImageResource(R.drawable.kogmaw_square_0);
        }	else if (name.equals("LeBlanc")) {
            imageView.setImageResource(R.drawable.leblanc_square_0);
        }	else if (name.equals("Lee Sin")) {
            imageView.setImageResource(R.drawable.leesin_square_0);
        }	else if (name.equals("Leona")) {
            imageView.setImageResource(R.drawable.leona_square_0);
        }	else if (name.equals("Lissandra")) {
            imageView.setImageResource(R.drawable.lissandra_square_0);
        }	else if (name.equals("Lucian")) {
            imageView.setImageResource(R.drawable.lucian_square_0);
        }	else if (name.equals("Lulu")) {
            imageView.setImageResource(R.drawable.lulu_square_0);
        }	else if (name.equals("Lux")) {
            imageView.setImageResource(R.drawable.lux_square_0);
        }	else if (name.equals("Malphite")) {
            imageView.setImageResource(R.drawable.malphite_square_0);
        }	else if (name.equals("Malzahar")) {
            imageView.setImageResource(R.drawable.malzahar_square_0);
        }	else if (name.equals("Maokai")) {
            imageView.setImageResource(R.drawable.maokai_square_0);
        }	else if (name.equals("Master Yi")) {
            imageView.setImageResource(R.drawable.masteryi_square_0);
        }	else if (name.equals("Miss Fortune")) {
            imageView.setImageResource(R.drawable.missfortune_square_0);
        }	else if (name.equals("Mordekaiser")) {
            imageView.setImageResource(R.drawable.mordekaiser_square_0);
        }	else if (name.equals("Morgana")) {
            imageView.setImageResource(R.drawable.morgana_square_0);
        }	else if (name.equals("Nami")) {
            imageView.setImageResource(R.drawable.nami_square_0);
        }	else if (name.equals("Nasus")) {
            imageView.setImageResource(R.drawable.nasus_square_0);
        }	else if (name.equals("Nautilus")) {
            imageView.setImageResource(R.drawable.nautilus_square_0);
        }	else if (name.equals("Nidalee")) {
            imageView.setImageResource(R.drawable.nidalee_square_0);
        }	else if (name.equals("Nocturne")) {
            imageView.setImageResource(R.drawable.nocturne_square_0);
        }	else if (name.equals("Nunu")) {
            imageView.setImageResource(R.drawable.nunu_square_0);
        }	else if (name.equals("Olaf")) {
            imageView.setImageResource(R.drawable.olaf_square_0);
        }	else if (name.equals("Orianna")) {
            imageView.setImageResource(R.drawable.orianna_square_0);
        }	else if (name.equals("Pantheon")) {
            imageView.setImageResource(R.drawable.pantheon_square_0);
        }	else if (name.equals("Poppy")) {
            imageView.setImageResource(R.drawable.poppy_square_0);
        }	else if (name.equals("Quinn")) {
            imageView.setImageResource(R.drawable.quinn_square_0);
        }	else if (name.equals("Rammus")) {
            imageView.setImageResource(R.drawable.rammus_square_0);
        }	else if (name.equals("Renekton")) {
            imageView.setImageResource(R.drawable.renekton_square_0);
        }	else if (name.equals("Rengar")) {
            imageView.setImageResource(R.drawable.rengar_square_0);
        }	else if (name.equals("Riven")) {
            imageView.setImageResource(R.drawable.riven_square_0);
        }	else if (name.equals("Rumble")) {
            imageView.setImageResource(R.drawable.rumble_square_0);
        }	else if (name.equals("Ryze")) {
            imageView.setImageResource(R.drawable.ryze_square_0);
        }	else if (name.equals("Sejuani")) {
            imageView.setImageResource(R.drawable.sejuani_square_0);
        }	else if (name.equals("Shaco")) {
            imageView.setImageResource(R.drawable.shaco_square_0);
        }	else if (name.equals("Shen")) {
            imageView.setImageResource(R.drawable.shen_square_0);
        }	else if (name.equals("Shyvana")) {
            imageView.setImageResource(R.drawable.shyvana_square_0);
        }	else if (name.equals("Singed")) {
            imageView.setImageResource(R.drawable.singed_square_0);
        }	else if (name.equals("Sion")) {
            imageView.setImageResource(R.drawable.sion_square_0);
        }	else if (name.equals("Sivir")) {
            imageView.setImageResource(R.drawable.sivir_square_0);
        }	else if (name.equals("Skarner")) {
            imageView.setImageResource(R.drawable.skarner_square_0);
        }	else if (name.equals("Sona")) {
            imageView.setImageResource(R.drawable.sona_square_0);
        }	else if (name.equals("Soraka")) {
            imageView.setImageResource(R.drawable.soraka_square_0);
        }	else if (name.equals("Swain")) {
            imageView.setImageResource(R.drawable.swain_square_0);
        }	else if (name.equals("Talon")) {
            imageView.setImageResource(R.drawable.talon_square_0);
        }	else if (name.equals("Taric")) {
            imageView.setImageResource(R.drawable.taric_square_0);
        }	else if (name.equals("Teemo")) {
            imageView.setImageResource(R.drawable.teemo_square_0);
        }	else if (name.equals("Thresh")) {
            imageView.setImageResource(R.drawable.thresh_square_0);
        }	else if (name.equals("Tristana")) {
            imageView.setImageResource(R.drawable.tristana_square_0);
        }	else if (name.equals("Trundle")) {
            imageView.setImageResource(R.drawable.trundle_square_0);
        }	else if (name.equals("Tryndamere")) {
            imageView.setImageResource(R.drawable.tryndamere_square_0);
        }	else if (name.equals("Twisted Fate")) {
            imageView.setImageResource(R.drawable.twistedfate_square_0);
        }	else if (name.equals("Twitch")) {
            imageView.setImageResource(R.drawable.twitch_square_0);
        }	else if (name.equals("Udyr")) {
            imageView.setImageResource(R.drawable.udyr_square_0);
        }	else if (name.equals("Urgot")) {
            imageView.setImageResource(R.drawable.urgot_square_0);
        }	else if (name.equals("Varus")) {
            imageView.setImageResource(R.drawable.varus_square_0);
        }	else if (name.equals("Vayne")) {
            imageView.setImageResource(R.drawable.vayne_square_0);
        }	else if (name.equals("Veigar")) {
            imageView.setImageResource(R.drawable.veigar_square_0);
        }	else if (name.equals("Vel'koz")) {
            imageView.setImageResource(R.drawable.velkoz_square_0);
        }	else if (name.equals("Vi")) {
            imageView.setImageResource(R.drawable.vi_square_0);
        }	else if (name.equals("Viktor")) {
            imageView.setImageResource(R.drawable.viktor_square_0);
        }	else if (name.equals("Vladimir")) {
            imageView.setImageResource(R.drawable.vladimir_square_0);
        }	else if (name.equals("Volibear")) {
            imageView.setImageResource(R.drawable.volibear_square_0);
        }	else if (name.equals("Warwick")) {
            imageView.setImageResource(R.drawable.warwick_square_0);
        }	else if (name.equals("Wukong")) {
            imageView.setImageResource(R.drawable.wukong_square_0);
        }	else if (name.equals("Xerath")) {
            imageView.setImageResource(R.drawable.xerath_square_0);
        }	else if (name.equals("Xin Zhao")) {
            imageView.setImageResource(R.drawable.xinzhao_square_0);
        }	else if (name.equals("Yasuo")) {
            imageView.setImageResource(R.drawable.yasuo_square_0);
        }	else if (name.equals("Yorick")) {
            imageView.setImageResource(R.drawable.yorick_square_0);
        }	else if (name.equals("Zac")) {
            imageView.setImageResource(R.drawable.zac_square_0);
        }	else if (name.equals("Zed")) {
            imageView.setImageResource(R.drawable.zed_square_0);
        }	else if (name.equals("Ziggs")) {
            imageView.setImageResource(R.drawable.ziggs_square_0);
        }	else if (name.equals("Zilean")) {
            imageView.setImageResource(R.drawable.zilean_square_0);
        }	else if (name.equals("Zyra")) {
            imageView.setImageResource(R.drawable.zyra_square_0);
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return champNames.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
}
