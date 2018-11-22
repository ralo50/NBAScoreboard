package com.ralo.nbascoreboard.Utils;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ralo.nbascoreboard.Fragments.BoxscoreFragment;
import com.ralo.nbascoreboard.Fragments.MatchupFragment;
import com.ralo.nbascoreboard.Fragments.PlaybyplayFragment;

import org.json.JSONObject;


public class SectionPagerAdapter extends FragmentPagerAdapter {
    JSONObject jsonObject;

    public SectionPagerAdapter(FragmentManager fm, JSONObject jsonObject) {
        super(fm);
        this.jsonObject = jsonObject;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MatchupFragment();
            case 1:
                return new BoxscoreFragment(this.jsonObject);
            case 2:
                return new PlaybyplayFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Match up";
            case 1:
                return "Box score";
            case 2:
                return "Play-by-play";
        }
        return null;
    }
}
