package com.ralo.nbascoreboard.Fragments.MainFragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ralo.nbascoreboard.Activities.GameActivity;
import com.ralo.nbascoreboard.Adapters.PlayAdapter;
import com.ralo.nbascoreboard.Adapters.PlayerAdapter;
import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.CardCreaters.PlayCardsCreater;
import com.ralo.nbascoreboard.Utils.DataClasses.Play;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonPlayParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class PlaybyplayFragment extends Fragment {

    private RecyclerView playRecyclerView;
    private PlayCardsCreater playCardsCreater;
    private JSONObject jsonObject;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Play> playArrayList;
    private Handler mHandler;
    private static final int REFRESH_TIME_DELAY = 20000;
    private MyTask myTask;
    private FloatingActionButton floatingActionButton;

    public PlaybyplayFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playbyplay, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        playRecyclerView = getView().findViewById(R.id.play_recycler_view);
        swipeRefreshLayout = getView().findViewById(R.id.swipe_refresh_layout);
        floatingActionButton = getView().findViewById(R.id.floating_action_button);
        floatingActionButton.setAlpha(0.5f);
        mHandler = new Handler();
        startRefreshingPlayByPlay();
        setRefreshLayoutListener();
    }

    private void setupPlayCardsCreater() {
        playCardsCreater = new PlayCardsCreater(jsonObject);
        playCardsCreater.populateCards();
        playArrayList = new ArrayList<>();
        playArrayList = playCardsCreater.getPlayArrayList();
        PlayAdapter adapter = new PlayAdapter(playArrayList);
        playRecyclerView.setHasFixedSize(true);
        playRecyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        playRecyclerView.setLayoutManager(llm);
    }

    private void setRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onRefresh() {
                startRefreshingPlayByPlay();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void startRefreshingPlayByPlay() {
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
        getNewGamePlayByPlay();
    }

    private void getNewGamePlayByPlay() {
        myTask = new PlaybyplayFragment.MyTask();
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
            String url = "http://data.nba.net/json/cms/noseason/game/" + GameActivity.gameDate + "/" + GameActivity.gameId + "/pbp_all.json";
            final RequestQueue requestQueue = Volley.newRequestQueue(NbaApp.getCurrentActivity());

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    jsonObject = response;
                    setupPlayCardsCreater();
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
            Toast.makeText(NbaApp.getCurrentActivity(), "Updated playbyplay", Toast.LENGTH_SHORT).show();
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

}
