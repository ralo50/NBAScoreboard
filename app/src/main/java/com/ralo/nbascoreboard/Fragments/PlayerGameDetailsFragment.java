package com.ralo.nbascoreboard.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.Player;


public class PlayerGameDetailsFragment extends DialogFragment {

    TextView playerName;
    Player player;

    public PlayerGameDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_game_details, container);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.PlayerFragmentGameDetails;
        Bundle bundle = getArguments();
        if (bundle != null) {
            player = (Player) bundle.getSerializable("player");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        playerName = getView().findViewById(R.id.playerName);
        String playerFullName = player.getFirstName() + " " + player.getLastName();
        playerName.setText(playerFullName);
    }
}