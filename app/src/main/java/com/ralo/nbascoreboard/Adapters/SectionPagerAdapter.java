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
    JSONObject jsonObject;
    public MatchupFragment matchupFragment;
    public BoxscoreFragment boxscoreFragment;
    public PlaybyplayFragment playbyplayFragment;

    public SectionPagerAdapter(FragmentManager fm, JSONObject jsonObject) {
        super(fm);
        this.jsonObject = jsonObject;
        matchupFragment = new MatchupFragment(this.jsonObject);
        boxscoreFragment = new BoxscoreFragment(this.jsonObject);
        playbyplayFragment = new PlaybyplayFragment(this.jsonObject);
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
