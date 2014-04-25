package com.lawl.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

/*
 * ChampFragment displays the main champion grid.
 * A gridView is initialized and each piece of the grid is passed a champion name.
 * The onChampClickListener interface is defined here, which calls
 * MainActivity to switch this fragment out when a champion is chosen.
 */


public class ChampFragment extends Fragment {

    private OnChampClickListener mListener;
    GridView gridView;

    // Array of champion names
    static final String[] CHAMPION_NAMES = new String[] {
            "Aatrox",
            "Ahri",
            "Akali",
            "Alistar",
            "Amumu",
            "Anivia",
            "Annie",
            "Ashe",
            "Blitzcrank",
            "Brand",
            "Caitlyn",
            "Cassiopeia",
            "Cho'gath",
            "Corki",
            "Darius",
            "Dr. Mundo",
            "Elise",
            "Evelynn",
            "Ezreal",
            "Fiddlesticks",
            "Fiora",
            "Fizz",
            "Galio",
            "Gangplank",
            "Garen",
            "Gragas",
            "Graves",
            "Hecarim",
            "Heimerdinger",
            "Irelia",
            "Janna",
            "Jarvan IV",
            "Jax",
            "Jayce",
            "Jinx",
            "Karma",
            "Karthus",
            "Kassadin",
            "Katarina",
            "Kayle",
            "Kennen",
            "Kog'Maw",
            "LeBlanc",
            "Lee Sin",
            "Leona",
            "Lissandra",
            "Lucian",
            "Lulu",
            "Lux",
            "Malphite",
            "Malzahar",
            "Maokai",
            "Master Yi",
            "Miss Fortune",
            "Mordekaiser",
            "Morgana",
            "Nami",
            "Nasus",
            "Nautilus",
            "Nidalee",
            "Nocturne",
            "Nunu",
            "Olaf",
            "Orianna",
            "Pantheon",
            "Poppy",
            "Quinn",
            "Rammus",
            "Renekton",
            "Rengar",
            "Riven",
            "Rumble",
            "Ryze",
            "Sejuani",
            "Shaco",
            "Shen",
            "Shyvana",
            "Singed",
            "Sion",
            "Sivir",
            "Skarner",
            "Sona",
            "Soraka",
            "Swain",
            "Talon",
            "Taric",
            "Teemo",
            "Thresh",
            "Tristana",
            "Trundle",
            "Tryndamere",
            "Twisted Fate",
            "Twitch",
            "Udyr",
            "Urgot",
            "Varus",
            "Vayne",
            "Veigar",
            "Vel'koz",
            "Vi",
            "Viktor",
            "Vladimir",
            "Volibear",
            "Warwick",
            "Wukong",
            "Xerath",
            "Xin Zhao",
            "Yasuo",
            "Yorick",
            "Zac",
            "Zed",
            "Ziggs",
            "Zilean",
            "Zyra"
    };

    public ChampFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_champ, container, false);

        // Create a new GridView
        gridView = (GridView) v.findViewById(R.id.grid_view);

        // Set each grid element to be defined by our GridAdapter
        gridView.setAdapter(new GridAdapter(v.getContext(), CHAMPION_NAMES));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.onChampClicked((((TextView) view.findViewById(R.id.grid_element_text)).getText()).toString());
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChampClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnChampClickListener {
        public void onChampClicked(String champ_name);
    }
}
