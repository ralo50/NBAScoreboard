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


    public TeamDetailFragment() {
        // Required empty public constructor
    }

    public static TeamDetailFragment newInstance(int teamId){
        Bundle args = new Bundle();
        args.putInt("teamId", teamId);

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


        ImageView imageView = getView().findViewById(R.id.teamLogo);

        Bundle args = getArguments();
        Log.d("teamId", String.valueOf(args.getInt("teamId")));
        Log.d("R.drawable.gsw", String.valueOf(R.drawable.gsw));
        imageView.setImageResource(R.drawable.gsw);
    }

}
