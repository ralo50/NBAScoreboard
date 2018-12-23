package com.ralo.nbascoreboard.Fragments.MainFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ralo.nbascoreboard.R;

import org.json.JSONObject;

public class PlaybyplayFragment extends Fragment {


    public PlaybyplayFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PlaybyplayFragment(JSONObject jsonObject) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playbyplay, container, false);
    }
}
