package com.ralo.nbascoreboard.Fragments.MainFragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
import com.ralo.nbascoreboard.Activities.PlayerActivity;
import com.ralo.nbascoreboard.Adapters.PlayerAdapter;
import com.ralo.nbascoreboard.Fragments.AuxiliaryFragments.PlayerGameDetailsFragment;
import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.DataClasses.Player;
import com.ralo.nbascoreboard.Utils.CardCreaters.PlayerCardsCreater;

import org.json.JSONObject;

import java.util.ArrayList;


public class BoxscoreFragment extends Fragment {

    private RadioGroup teamRadioGroup;
    private JSONObject jsonObject;
    private RecyclerView myRecyclerView;
    private ArrayList<Player> playerArrayList;
    private PlayerCardsCreater playerCardsCreater;
    private RadioButton homeRadioButton;
    private RadioButton awayRadioButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyTask myTask;
    private boolean homeTeamSelected;
    private static final int REFRESH_TIME_DELAY = 20000;
    private Handler mHandler;

    public BoxscoreFragment() {
    }

    @SuppressLint("ValidFragment")
    public BoxscoreFragment(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boxscore, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (jsonObject != null) {
            setupViews();
            initialGameRefresh();
        }
    }

    private void setupViews() {
        teamRadioGroup = getView().findViewById(R.id.toggle);
        myRecyclerView = getView().findViewById(R.id.myRecyclerView);
        homeRadioButton = getView().findViewById(R.id.homeTeamRadioButton);
        awayRadioButton = getView().findViewById(R.id.awayTeamRadioButton);
        swipeRefreshLayout = getView().findViewById(R.id.swipeRefreshLayout);
        mHandler = new Handler();
        setupPlayerDetails();
        setRefreshLayoutListener();
        getTeamNames();
        setupAwayPlayersDetails();
        setupTeamDetails(jsonObject);
    }

    private void setupPlayerDetails() {
        teamRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.awayTeamRadioButton:
                        setupAwayPlayersDetails();
                        homeTeamSelected = false;
                        break;
                    case R.id.homeTeamRadioButton:
                        setupHomePlayersDetails();
                        homeTeamSelected = true;
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

    private void setupTeamDetails(JSONObject jsonObject) {
        JsonTeamParser teamParser = new JsonTeamParser(jsonObject);
        if (GameActivity.isGameOver) {
            GameFragment.gameTimeTextView.setText(R.string.game_ended);
        } else if (GameActivity.isGameActivated) {
            GameFragment.gameTimeTextView.setTextColor(Color.parseColor("#ff0000"));
            GameFragment.gameTimeTextView.setText(R.string.game_live);
        }
        GameFragment.awayTeamScoreTextView.setText(String.valueOf(teamParser.getTeamScore("visitor")));
        GameFragment.homeTeamScoreTextView.setText(String.valueOf(teamParser.getTeamScore("home")));
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setCardsCreater() {
        playerArrayList = new ArrayList<>();
        playerArrayList = playerCardsCreater.getPlayerArrayList();
        PlayerAdapter adapter = new PlayerAdapter(playerArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                setupPlayerGameDetailsFragment(position);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                setupPlayerCareerDetailsFragment(position);
            }
        });
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
        runLayoutAnimation(myRecyclerView);
    }

    private void setupPlayerGameDetailsFragment(int position) {
        PlayerGameDetailsFragment playerGameDetailsFragment = new PlayerGameDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("player", playerArrayList.get(position));
        playerGameDetailsFragment.setArguments(bundle);
        playerGameDetailsFragment.show(getChildFragmentManager(), "dialog_fragment");
    }

    private void setupPlayerCareerDetailsFragment(int position) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra("Player", playerArrayList.get(position));
        NbaApp.getCurrentActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(intent);
    }

    private void getTeamNames() {
        if (jsonObject != null) {
            JsonTeamParser jsonTeamParser = new JsonTeamParser(jsonObject);
            homeRadioButton.setText(jsonTeamParser.getTeamName("home"));
            awayRadioButton.setText(jsonTeamParser.getTeamName("visitor"));
        }
    }

    private void setRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onRefresh() {
                startRefreshingGameStats();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initialGameRefresh() {
        if (GameActivity.isGameActivated)
            startRefreshingGameStats();
    }

    private void startRefreshingGameStats() {
        if (GameActivity.isGameActivated)
            mStatusChecker.run();
        else
            refreshFragment();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                refreshFragment();
            } finally {
                mHandler.postDelayed(mStatusChecker, REFRESH_TIME_DELAY);
            }
        }
    };

    private void refreshFragment() {
        getNewGameStats();
    }

    private void getNewGameStats() {
        myTask = new MyTask();
        myTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "http://data.nba.net/json/cms/noseason/game/" + GameActivity.gameDate + "/" + GameActivity.gameId + "/boxscore.json";
            final RequestQueue requestQueue = Volley.newRequestQueue(NbaApp.getCurrentActivity());

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    jsonObject = response;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(NbaApp.getCurrentActivity(), "Error updating stats", Toast.LENGTH_SHORT).show();
                    mHandler.removeCallbacks(mStatusChecker);
                }
            });
            requestQueue.add(objectRequest);
            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (homeTeamSelected)
                setupHomePlayersDetails();
            else
                setupAwayPlayersDetails();
            Toast.makeText(NbaApp.getCurrentActivity(), "Updated", Toast.LENGTH_SHORT).show();
            setupTeamDetails(jsonObject);
        }
    }

    void stopRefreshingGameStats() {
        if (mHandler != null)
            mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopRefreshingGameStats();
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
