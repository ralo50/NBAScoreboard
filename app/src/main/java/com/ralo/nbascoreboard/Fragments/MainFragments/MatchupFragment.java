package com.ralo.nbascoreboard.Fragments.MainFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ralo.nbascoreboard.Adapters.GameStatAdapter;
import com.ralo.nbascoreboard.Adapters.PeriodAdapter;
import com.ralo.nbascoreboard.Adapters.PlayerAdapter;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.CardCreaters.GameStatCardsCreater;
import com.ralo.nbascoreboard.Utils.DataClasses.GameStat;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonPeriodParser;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonTeamParser;
import com.ralo.nbascoreboard.Utils.DataClasses.Period;
import com.ralo.nbascoreboard.Utils.CardCreaters.PeriodCardsCreater;

import org.json.JSONObject;

import java.util.ArrayList;

public class MatchupFragment extends Fragment {

    private JSONObject jsonObject;
    private JsonPeriodParser jsonPeriodParser;
    private PeriodCardsCreater periodCardsCreater;
    private ArrayList<Period> periodArrayList;
    private RecyclerView periodRecyclerView;
    private TextView awayTeamPeriodName;
    private TextView homeTeamPeriodName;
    private JsonTeamParser jsonTeamParser;
    private GameStatCardsCreater gameStatCardsCreater;
    private ArrayList<GameStat> gameStatArrayList;
    private RecyclerView gameStatRecyclerView;
    private TextView awayTeamGameStatName;
    private TextView homeTeamGameStatName;

    public MatchupFragment() {
    }

    @SuppressLint("ValidFragment")
    public MatchupFragment(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matchup, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (jsonObject != null) {
            setupViews();
        }
    }

    private void setupViews() {
        jsonTeamParser = new JsonTeamParser(jsonObject);
        awayTeamPeriodName = getView().findViewById(R.id.awayTeamPeriodPoints);
        homeTeamPeriodName = getView().findViewById(R.id.homeTeamPeriodPoints);
        awayTeamGameStatName = getView().findViewById(R.id.away_team_stat);
        awayTeamGameStatName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        homeTeamGameStatName = getView().findViewById(R.id.home_team_stat);
        homeTeamGameStatName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        awayTeamGameStatName.setText(jsonTeamParser.getTeamName("visitor"));
        homeTeamGameStatName.setText(jsonTeamParser.getTeamName("home"));
        awayTeamPeriodName.setText(jsonTeamParser.getTeamName("visitor"));
        homeTeamPeriodName.setText(jsonTeamParser.getTeamName("home"));
        periodRecyclerView = getView().findViewById(R.id.periodRecyclerView);
        gameStatRecyclerView = getView().findViewById(R.id.gameStatsRecyclerView);

        setupPeriodRecyclerView();
        setPeriodCardsCreater();

        setupGameStatsRecyclerView();
        setGameStatsCardsCreater();
    }

    private void setupPeriodRecyclerView() {
        jsonPeriodParser = new JsonPeriodParser(jsonObject);
        periodCardsCreater = new PeriodCardsCreater(jsonObject, "visitor");
        periodCardsCreater.populateCards();
    }

    public void setPeriodCardsCreater() {
        periodArrayList = new ArrayList<>();
        periodArrayList = periodCardsCreater.getPeriodArrayList();
        PeriodAdapter adapter = new PeriodAdapter(periodArrayList);
        periodRecyclerView.setHasFixedSize(true);
        periodRecyclerView.setNestedScrollingEnabled(false);
        periodRecyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(NbaApp.getCurrentActivity(), periodArrayList.size());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        periodRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setupGameStatsRecyclerView() {
        jsonTeamParser = new JsonTeamParser(jsonObject);
        gameStatCardsCreater = new GameStatCardsCreater(jsonObject, "visitor");
        gameStatCardsCreater.populateCards();
    }

    private void setGameStatsCardsCreater() {
        gameStatArrayList = new ArrayList<>();
        gameStatArrayList = gameStatCardsCreater.getGameStatArrayList();
        GameStatAdapter adapter = new GameStatAdapter(gameStatArrayList);
        gameStatRecyclerView.setHasFixedSize(true);
        gameStatRecyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        gameStatRecyclerView.setLayoutManager(llm);
    }
}
