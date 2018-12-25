package com.ralo.nbascoreboard.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ralo.nbascoreboard.Fragments.MainFragments.BoxscoreFragment;
import com.ralo.nbascoreboard.Fragments.MainFragments.MatchupFragment;
import com.ralo.nbascoreboard.Fragments.MainFragments.PlaybyplayFragment;

import org.json.JSONObject;


public class SectionPagerAdapter extends FragmentPagerAdapter {
    private JSONObject jsonObject;
    private MatchupFragment matchupFragment;
    private BoxscoreFragment boxscoreFragment;
    private PlaybyplayFragment playbyplayFragment;

    public SectionPagerAdapter(FragmentManager fm, JSONObject jsonObject) {
        super(fm);
        this.jsonObject = jsonObject;
        matchupFragment = new MatchupFragment(this.jsonObject);
        boxscoreFragment = new BoxscoreFragment(this.jsonObject);
        playbyplayFragment = new PlaybyplayFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return matchupFragment;
            case 1:
                return boxscoreFragment;
            case 2:
                return playbyplayFragment;
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
