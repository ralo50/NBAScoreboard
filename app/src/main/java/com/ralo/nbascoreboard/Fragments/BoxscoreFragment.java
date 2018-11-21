package com.ralo.nbascoreboard.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonTeamParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoxscoreFragment extends Fragment {

    TextView view;
    JsonTeamParser parser;


    public BoxscoreFragment(){}
    @SuppressLint("ValidFragment")
    public BoxscoreFragment(JsonTeamParser parser) {
        this.parser = parser;
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

        view = getView().findViewById(R.id.foreignText);
        view.setText(parser.getAwayTeamName());
    }
}
