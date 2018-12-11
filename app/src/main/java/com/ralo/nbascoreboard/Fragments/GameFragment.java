package com.ralo.nbascoreboard.Fragments;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ralo.nbascoreboard.Activities.GameActivity;
import com.ralo.nbascoreboard.Activities.TeamActivity;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.CustomViewPager;
import com.ralo.nbascoreboard.Utils.JsonPlayerParser;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.SectionPagerAdapter;

import org.json.JSONObject;

public class GameFragment extends Fragment {

    private View gameFragmentView;
    private AdView adView;
    private TextView homeTeamNameTextView;
    private TextView awayTeamNameTextView;
    private ImageView awayTeamLogoImageView;
    private ImageView homeTeamLogoImageView;
    public static TextView awayTeamScoreTextView;
    public static TextView homeTeamScoreTextView;
    private TextView awayTeamWinsTextView;
    private TextView homeTeamWinsTextView;
    private TextView gameTimeTextView;
    private int homeTeamStringId;
    private int awayTeamStringId;
    private JsonPlayerParser playerParser;
    private SectionPagerAdapter pagerAdapter;
    private JSONObject jsonObject;
    private CustomViewPager pager;
    private TabLayout tabLayout;

    public GameFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gameFragmentView = inflater.inflate(R.layout.fragment_game, container, false);
        findViews();
        initAd();
        return gameFragmentView;
    }

    private void findViews() {
        adView = gameFragmentView.findViewById(R.id.adView);
        homeTeamNameTextView = gameFragmentView.findViewById(R.id.hometeamname);
        awayTeamNameTextView = gameFragmentView.findViewById(R.id.awayteamname);
        homeTeamScoreTextView = gameFragmentView.findViewById(R.id.hometeamscore);
        awayTeamScoreTextView = gameFragmentView.findViewById(R.id.awayteamscore);
        homeTeamWinsTextView = gameFragmentView.findViewById(R.id.hometeamwins);
        awayTeamWinsTextView = gameFragmentView.findViewById(R.id.awayteamwins);
        gameTimeTextView = gameFragmentView.findViewById(R.id.gameTime);
        pager = gameFragmentView.findViewById(R.id.pager);
        tabLayout = gameFragmentView.findViewById(R.id.tabLayout);
        awayTeamLogoImageView = gameFragmentView.findViewById(R.id.awayteamlogo);
        awayTeamLogoImageView.setOnClickListener(onLogoClicked);
        homeTeamLogoImageView = gameFragmentView.findViewById(R.id.hometeamlogo);
        homeTeamLogoImageView.setOnClickListener(onLogoClicked);
        load();
    }

    private void load() {
        getGameDetailsById();
    }

    private void getGameDetailsById() {
        String url = "http://data.nba.net/json/cms/noseason/game/" + GameActivity.gameDate + "/" + GameActivity.gameId + "/boxscore.json";
        final RequestQueue requestQueue = Volley.newRequestQueue(NbaApp.getCurrentActivity());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                jsonObject = response;
                                setViewData(response);
                                setupFragments(response);
                            }
                        });
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (jsonObject != null) {
                    setViewData(jsonObject);
                    setupFragments(jsonObject);
                }
            }
        });
        requestQueue.add(objectRequest);
    }

    private void setViewData(JSONObject jsonObject) {
        JsonTeamParser teamParser = new JsonTeamParser(jsonObject);
        playerParser = new JsonPlayerParser(jsonObject);
        awayTeamLogoImageView.setImageResource(teamParser.getTeamImage("visitor"));
        homeTeamLogoImageView.setImageResource(teamParser.getTeamImage("home"));
        awayTeamStringId = teamParser.getTeamImage("visitor");
        homeTeamStringId = teamParser.getTeamImage("home");
        awayTeamNameTextView.setText(teamParser.getTeamName("visitor"));
        homeTeamNameTextView.setText(teamParser.getTeamName("home"));
        awayTeamScoreTextView.setText(String.valueOf(teamParser.getTeamScore("visitor")));
        homeTeamScoreTextView.setText(String.valueOf(teamParser.getTeamScore("home")));
        homeTeamWinsTextView.setText(GameActivity.homeTeamWins);
        awayTeamWinsTextView.setText(GameActivity.awayTeamWins);
    }


    private void setupFragments(JSONObject jsonObject) {
        tabLayout.setupWithViewPager(pager);
        pagerAdapter = new SectionPagerAdapter(getFragmentManager(), jsonObject);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1);
    }

    private View.OnClickListener onLogoClicked = new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.awayteamlogo:
                    Intent intent = new Intent(getActivity(), TeamActivity.class);
                    intent.putExtra("isHome", false);
                    intent.putExtra("awayTeamLogoImageView", awayTeamStringId);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<ImageView, String>(awayTeamLogoImageView, "awayTeamLogoImageView");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs[0]);
                    startActivity(intent, options.toBundle());
                    break;
                case R.id.hometeamlogo:
                    intent = new Intent(getActivity(), TeamActivity.class);
                    intent.putExtra("homeTeamLogoImageView", homeTeamStringId);
                    intent.putExtra("isHome", true);
                    pairs = new Pair[1];
                    pairs[0] = new Pair<ImageView, String>(homeTeamLogoImageView, "homeTeamLogoImageView");
                    options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs[0]);
                    startActivity(intent, options.toBundle());
                    break;
            }
        }
    };

    private void initAd() {
        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("8623F0A041C7A59B4C3BA8CAD7B28F64").build();
        adView.loadAd(adRequest);
    }
}
