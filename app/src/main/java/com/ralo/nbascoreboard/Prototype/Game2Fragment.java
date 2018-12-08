package com.ralo.nbascoreboard.Prototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ralo.nbascoreboard.Activities.GameActivity;
import com.ralo.nbascoreboard.Fragments.BoxscoreFragment;
import com.ralo.nbascoreboard.Fragments.MatchupFragment;
import com.ralo.nbascoreboard.Fragments.PlaybyplayFragment;
import com.ralo.nbascoreboard.Fragments.TeamDetailFragment;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonPlayerParser;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.TeamDetailsTransition;

import org.json.JSONObject;

public class Game2Fragment extends Fragment {
    private View gameFragmentView;
    private GameActivity gameActivity;
    private NbaTabView tabGameDetails;
    private TextView homeTeamNameTextView;
    private TextView awayTeamNameTextView;
    private ImageView awayTeamLogoImageView;
    private ImageView homeTeamLogoImageView;
    private TextView awayTeamWinsTextView;
    private TextView homeTeamWinsTextView;
    private TextView gameTimeTextView;
    private int homeTeamStringId;
    private int awayTeamStringId;
    private JsonPlayerParser playerParser;
    private JSONObject jsonObject;
    private FrameLayout tabFragmentContainer;
    public MatchupFragment matchupFragment;
    public static TextView awayTeamScoreTextView;
    public static TextView homeTeamScoreTextView;
    public BoxscoreFragment boxscoreFragment;
    public PlaybyplayFragment playbyplayFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gameFragmentView = inflater.inflate(R.layout.fragment_game2, container, false);
        gameActivity = (GameActivity) getActivity();
        setup();
        return gameFragmentView;
    }

    private void setup() {
        tabFragmentContainer = gameFragmentView.findViewById(R.id.tab_fragment_container);
        tabGameDetails = gameFragmentView.findViewById(R.id.tab_game_details);
        homeTeamNameTextView = gameFragmentView.findViewById(R.id.hometeamname);
        awayTeamNameTextView = gameFragmentView.findViewById(R.id.awayteamname);
        homeTeamScoreTextView = gameFragmentView.findViewById(R.id.hometeamscore);
        awayTeamScoreTextView = gameFragmentView.findViewById(R.id.awayteamscore);
        homeTeamWinsTextView = gameFragmentView.findViewById(R.id.hometeamwins);
        awayTeamWinsTextView = gameFragmentView.findViewById(R.id.awayteamwins);
        awayTeamLogoImageView = gameFragmentView.findViewById(R.id.awayteamlogo);
        homeTeamWinsTextView = gameFragmentView.findViewById(R.id.hometeamwins);
        awayTeamWinsTextView = gameFragmentView.findViewById(R.id.awayteamwins);
        awayTeamLogoImageView.setOnClickListener(onLogoClicked);
        homeTeamLogoImageView = gameFragmentView.findViewById(R.id.hometeamlogo);
        homeTeamLogoImageView.setOnClickListener(onLogoClicked);
        gameTimeTextView = gameFragmentView.findViewById(R.id.gameTime);
        load();
    }

    private void load() {
        getGameDetailsById();
    }

    private void getGameDetailsById() {
        String url = "http://data.nba.net/json/cms/noseason/game/" + gameActivity.gameDate + "/" + gameActivity.gameId + "/boxscore.json";
        final RequestQueue requestQueue = Volley.newRequestQueue(gameActivity);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setViewData(response);
                                jsonObject = response;
                                setupTabFragments();
                            }
                        });
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(jsonObject != null) {
                    setViewData(jsonObject);
                    setupTabFragments();
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

    private void setupTabFragments() {
        matchupFragment = new MatchupFragment();
        boxscoreFragment = new BoxscoreFragment(jsonObject);
        playbyplayFragment = new PlaybyplayFragment();
        tabGameDetails.setTab0(matchupFragment, "Matchup");
        tabGameDetails.setTab1(boxscoreFragment, "BoxScore");
        tabGameDetails.setTab2(playbyplayFragment, "PlayByPlay");
        NbaTabView.loadFragmentById(1);
    }

    private View.OnClickListener onLogoClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.awayteamlogo:

                    TeamDetailFragment teamDetailFragment = TeamDetailFragment.newInstance(awayTeamStringId, false);
                    teamDetailFragment.setSharedElementEnterTransition(new TeamDetailsTransition());
                    teamDetailFragment.setEnterTransition(new Fade().setDuration(1));

                    setExitTransition(new Fade());
                    teamDetailFragment.setSharedElementReturnTransition(new TeamDetailsTransition());

                    getActivity().getSupportFragmentManager().beginTransaction().
                            addSharedElement(awayTeamLogoImageView, "awayTeamLogoImageView").
                            replace(R.id.fragment_container, teamDetailFragment).addToBackStack(null).commit();
                    break;

                case R.id.hometeamlogo:

                    teamDetailFragment = TeamDetailFragment.newInstance(homeTeamStringId, true);
                    teamDetailFragment.setSharedElementEnterTransition(new TeamDetailsTransition());
                    teamDetailFragment.setEnterTransition(new Fade().setDuration(1));

                    setExitTransition(new Fade());
                    teamDetailFragment.setSharedElementReturnTransition(new TeamDetailsTransition());

                    getActivity().getSupportFragmentManager().beginTransaction().
                            addSharedElement(homeTeamLogoImageView, "homeTeamLogoImageView").
                            replace(R.id.fragment_container, teamDetailFragment).addToBackStack(null).commit();
                    break;
            }
        }
    };

}
