package com.ralo.nbascoreboard.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ralo.nbascoreboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamDetailFragment extends Fragment {

    ImageView imageView;
    public TeamDetailFragment() {
        // Required empty public constructor
    }

    public static TeamDetailFragment newInstance(int teamId, boolean isHomeTeam){
        Bundle args = new Bundle();
        args.putInt("teamId", teamId);
        args.putBoolean("homeTeam", isHomeTeam);

        TeamDetailFragment fragment = new TeamDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        if(args.getBoolean("homeTeam")){
            imageView = getView().findViewById(R.id.teamLogoHome);
            imageView.setImageResource(args.getInt("teamId"));
        }
        else{
            imageView = getView().findViewById(R.id.teamLogoAway);
            imageView.setImageResource(args.getInt("teamId"));
        }
    }

}
