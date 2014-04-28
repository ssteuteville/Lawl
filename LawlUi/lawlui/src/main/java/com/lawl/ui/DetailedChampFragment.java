package com.lawl.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * DetailedChampFragment is the detailed champion page.
 * It contains a Swipe UI for skills, tips, and lore
 */

public class DetailedChampFragment extends Fragment {

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private String champ_name;


    public DetailedChampFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detailed_champ, container, false);

        // Get champion name from main activity
        Bundle args = getArguments();
        if (args != null) {
            champ_name = args.getString("CHAMP_NAME");
        }
        else champ_name = "error";

        // Create a new ViewPager
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager(), champ_name);
        viewPager.setAdapter(pagerAdapter);

        return v;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private String champ_name;

        public ScreenSlidePagerAdapter(FragmentManager fm, String string) {
            super(fm);
            champ_name = string;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putString("CHAMP_NAME", champ_name);
            switch (position) {
                case 0:
                    SkillChampFragment skillChampFragment = new SkillChampFragment();
                    skillChampFragment.setArguments(args);
                    return skillChampFragment;
                case 1:
                    TipsChampFragment tipsChampFragment  = new TipsChampFragment();
                    tipsChampFragment.setArguments(args);
                    return tipsChampFragment;
                case 2: LoreChampFragment loreChampFragment = new LoreChampFragment();
                    loreChampFragment.setArguments(args);
                    return loreChampFragment;
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
