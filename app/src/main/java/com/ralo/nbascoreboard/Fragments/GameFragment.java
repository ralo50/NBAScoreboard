package com.ralo.nbascoreboard.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.CardsCreater;
import com.ralo.nbascoreboard.Utils.JsonGameParser;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.SectionPagerAdapter;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener{
    AdView adView;
    String gameDate;
    String gameId;
    TextView gameDateTextView;
    TextView gameIdTextView;
    TextView homeTeamNameTextView;
    TextView awayTeamNameTextView;
    ImageView awayTeamLogoImageView;
    ImageView homeTeamLogoImageView;
    TextView awayTeamScoreTextView;
    TextView homeTeamScoreTextView;
    TextView awayTeamWinsTextView;
    TextView homeTeamWinsTextView;
    TextView gameTimeTextView;


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
        findViews();


        gameDateTextView.setText(gameDate);
        gameIdTextView.setText(gameId);
        adView = getView().findViewById(R.id.adView);
        initAd();
        setupFragments();
        setupJsonTeamParser();
    }

    private void findViews() {
        gameDateTextView = getView().findViewById(R.id.gameDate);
        gameIdTextView = getView().findViewById(R.id.gameId);
        homeTeamNameTextView = getView().findViewById(R.id.hometeamname);
        awayTeamNameTextView = getView().findViewById(R.id.awayteamname);
        homeTeamScoreTextView = getView().findViewById(R.id.hometeamscore);
        awayTeamScoreTextView = getView().findViewById(R.id.awayteamscore);
        homeTeamWinsTextView = getView().findViewById(R.id.hometeamwins);
        awayTeamWinsTextView = getView().findViewById(R.id.awayteamwins);
        awayTeamLogoImageView = getView().findViewById(R.id.awayteamlogo);
        homeTeamLogoImageView = getView().findViewById(R.id.hometeamlogo);
        gameTimeTextView = getView().findViewById(R.id.gameTime);
    }

    private void setupJsonTeamParser() {
        String url = "http://data.nba.net/json/cms/noseason/game/" + gameDate + "/" + gameId + "/boxscore.json";
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final JsonTeamParser parser = new JsonTeamParser(response);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setViewData(parser);
                                }
                            });
                        }
                    }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);
    }
    private void setViewData(JsonTeamParser parser){
        awayTeamLogoImageView.setImageResource(parser.getAwayTeamImage());
        homeTeamLogoImageView.setImageResource(parser.getHomeTeamImage());
        awayTeamNameTextView.setText(parser.getAwayTeamName());
        homeTeamNameTextView.setText(parser.getHomeTeamName());
        awayTeamScoreTextView.setText(parser.getAwayTeamScore());
        homeTeamScoreTextView.setText(parser.getHomeTeamScore());
    }


    private void initAd() {
        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("8623F0A041C7A59B4C3BA8CAD7B28F64").build();
        adView.loadAd(adRequest);
    }

    private void setupFragments(){
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getFragmentManager());
        ViewPager pager = getView().findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1);

        TabLayout tabLayout = getView().findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);

        ImageView awayTeamLogo =  getView().findViewById(R.id.awayteamlogo);
        awayTeamLogo.setOnClickListener(this);

        ImageView homeTeamLogo =  getView().findViewById(R.id.hometeamlogo);
        homeTeamLogo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.awayteamlogo:
                Toast.makeText(getActivity(), "Hi", Toast.LENGTH_SHORT).show();
                break;

            case R.id.hometeamlogo:
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
