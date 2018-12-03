package com.ralo.nbascoreboard.Fragments;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ralo.nbascoreboard.Activities.GameActivity;
import com.ralo.nbascoreboard.Adapters.PlayerAdapter;
import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.Prototype.Game2Fragment;
import com.ralo.nbascoreboard.Prototype.NbaTabView;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.Player;
import com.ralo.nbascoreboard.Utils.PlayerCardsCreater;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoxscoreFragment extends Fragment {

    RadioGroup teamRadioGroup;
    JSONObject jsonObject;
    RecyclerView myRecyclerView;
    ArrayList<Player> playerArrayList;
    PlayerCardsCreater playerCardsCreater;
    RadioButton homeRadioButton;
    RadioButton awayRadioButton;
    ConstraintLayout constraintLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean homeTeamSelected;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    public BoxscoreFragment() {
    }

    @SuppressLint("ValidFragment")
    public BoxscoreFragment(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boxscore, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        setupPlayerDetails();
    }

    private void setupViews() {
        teamRadioGroup = getView().findViewById(R.id.toggle);
        myRecyclerView = getView().findViewById(R.id.myRecyclerView);
        homeRadioButton = getView().findViewById(R.id.homeTeamRadioButton);
        awayRadioButton = getView().findViewById(R.id.awayTeamRadioButton);
        constraintLayout = getView().findViewById(R.id.constraint_layout);
        swipeRefreshLayout = getView().findViewById(R.id.swipeRefreshLayout);
        setRefreshLayoutListener();
        getTeamNames();
        setupAwayPlayersDetails();
    }


    private void setupPlayerDetails() {
        teamRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.awayTeamRadioButton:
                        setupAwayPlayersDetails();
                        homeTeamSelected = false;
                        //something
                        break;
                    case R.id.homeTeamRadioButton:
                        setupHomePlayersDetails();
                        homeTeamSelected = true;
                        //something
                        break;
                }
            }
        });
    }

    private void setupAwayPlayersDetails() {
        playerCardsCreater = new PlayerCardsCreater(jsonObject, "visitor");
        playerCardsCreater.populateCards();
        setCardsCreater();
    }

    private void setupHomePlayersDetails() {
        playerCardsCreater = new PlayerCardsCreater(jsonObject, "home");
        playerCardsCreater.populateCards();
        setCardsCreater();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setCardsCreater() {
        playerArrayList = new ArrayList<>();
        playerArrayList = playerCardsCreater.getPlayerArrayList();
        PlayerAdapter adapter = new PlayerAdapter(playerArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), String.valueOf(playerArrayList.get(position).getPersonId()), Toast.LENGTH_SHORT).show();
                //TODO setup fragment for player information
            }
        });
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);
        setRecyclerViewSwipeListener(myRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
    }

    private void getTeamNames() {
        JsonTeamParser jsonTeamParser = new JsonTeamParser(jsonObject);
        homeRadioButton.setText(jsonTeamParser.getTeamName("home"));
        awayRadioButton.setText(jsonTeamParser.getTeamName("visitor"));
    }

    private void setRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onRefresh() {
                refreshFragment();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void refreshFragment() {
        getNewGameStats();
    }

    private void getNewGameStats() {
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
                                if (homeTeamSelected)
                                    setupHomePlayersDetails();
                                else
                                    setupAwayPlayersDetails();
                                JsonTeamParser teamParser = new JsonTeamParser(jsonObject);
                                Game2Fragment.awayTeamScoreTextView.setText(String.valueOf(teamParser.getTeamScore("visitor")));
                                Game2Fragment.homeTeamScoreTextView.setText(String.valueOf(teamParser.getTeamScore("home")));
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

    private void setRecyclerViewSwipeListener(RecyclerView myRecyclerView) {
        myRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = motionEvent.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE)
                        {
                            if (x2 > x1)
                                NbaTabView.setPreviousFragment(1);
                            else
                                NbaTabView.setNextFragment(1);
                        }
                        break;
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {}
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {}
        });
    }
}
