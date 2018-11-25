package com.ralo.nbascoreboard.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoxscoreFragment extends Fragment {

    TextView view;
    JSONObject jsonObject;
    ListView detailsListView;


    public BoxscoreFragment(){}
    @SuppressLint("ValidFragment")
    public BoxscoreFragment(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boxscore, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();

    }

    private void setupViews(){
        JsonTeamParser parser = new JsonTeamParser(jsonObject);
        view = getView().findViewById(R.id.foreignText);
        view.setText(parser.getAwayTeamName());
        detailsListView = getView().findViewById(R.id.detailsListView);

    }
}
