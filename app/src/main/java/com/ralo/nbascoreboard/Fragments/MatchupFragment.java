package com.ralo.nbascoreboard.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ralo.nbascoreboard.Adapters.PeriodAdapter;
import com.ralo.nbascoreboard.Adapters.PlayerAdapter;
import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonPeriodParser;
import com.ralo.nbascoreboard.Utils.Period;
import com.ralo.nbascoreboard.Utils.PeriodCardsCreater;
import com.ralo.nbascoreboard.Utils.PlayerCardsCreater;

import org.json.JSONObject;

import java.util.ArrayList;


public class MatchupFragment extends Fragment {
    JSONObject jsonObject;
    JsonPeriodParser jsonPeriodParser;
    PeriodCardsCreater periodCardsCreater;
    ArrayList<Period> periodArrayList;
    RecyclerView myRecyclerView;

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
        setupViews();
    }

    private void setupViews() {
        myRecyclerView = getView().findViewById(R.id.periodRecyclerView);
        jsonPeriodParser = new JsonPeriodParser(jsonObject);
        periodCardsCreater = new PeriodCardsCreater(jsonObject, "visitor");
        periodCardsCreater.populateCards();
        setCardsCreater();
        Log.d("hello", String.valueOf(jsonPeriodParser.getNumberOfPeriods()));
    }

    public void setCardsCreater() {
        periodArrayList = new ArrayList<>();
        periodArrayList = periodCardsCreater.getPeriodArrayList();
        PeriodAdapter adapter = new PeriodAdapter(periodArrayList);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);
        // setRecyclerViewSwipeListener(myRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(llm);
    }

}
