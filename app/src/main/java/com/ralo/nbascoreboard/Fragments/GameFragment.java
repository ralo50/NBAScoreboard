package com.ralo.nbascoreboard.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ralo.nbascoreboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    AdView adView;
    String gameDate;
    String gameId;
    TextView gameDateTextView;
    TextView gameIdTextView;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        gameDate = getArguments().getString("gameDate");
        gameId = getArguments().getString("gameId");
        Log.d("hi", "gamedate:" + gameDate);
        Log.d("hi", "gameid:" + gameId);
        return inflater.inflate(R.layout.fragment_game, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gameDateTextView = getView().findViewById(R.id.gameDate);
        gameIdTextView = getView().findViewById(R.id.gameId);

        gameDateTextView.setText(gameDate);
        gameIdTextView.setText(gameId);
        adView = getView().findViewById(R.id.adView);
        initAd();
    }


    private void initAd() {
        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("8623F0A041C7A59B4C3BA8CAD7B28F64").build();
        adView.loadAd(adRequest);
    }

}
