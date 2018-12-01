package com.ralo.nbascoreboard.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.Player;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder>{
    private ArrayList<Player> playerList;


    public PlayerAdapter(ArrayList<Player> myPlayers){
        this.playerList = myPlayers;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView playerName;
        private TextView playerPoints;
        private TextView playerRebounds;
        private TextView playerAssists;
        private TextView playerFieldGoals;
        private TextView playerThreePointers;
        private TextView playerFreeThrows;
        private TextView minutesPlayed;

        MyViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerPoints = itemView.findViewById(R.id.player_points);
            playerRebounds = itemView.findViewById(R.id.player_rebounds);
            playerAssists = itemView.findViewById(R.id.player_assists);
            playerFieldGoals = itemView.findViewById(R.id.player_field_goals);
            playerThreePointers = itemView.findViewById(R.id.player_three_pointers);
            playerFreeThrows = itemView.findViewById(R.id.player_free_throws);
            minutesPlayed = itemView.findViewById(R.id.player_minutes);
        }
    }

    @NonNull
    @Override
    public PlayerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_card_view, parent, false);
        final PlayerAdapter.MyViewHolder myViewHolder = new PlayerAdapter.MyViewHolder(listItem);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Player player = playerList.get(position);
        myViewHolder.playerName.setText(String.valueOf(player.getLastName()));
        myViewHolder.playerPoints.setText(String.valueOf(player.getPoints()));
        myViewHolder.playerRebounds.setText(String.valueOf(player.getReboundsTotal()));
        myViewHolder.playerAssists.setText(String.valueOf(player.getAssists()));
        myViewHolder.playerFieldGoals.setText(String.valueOf(player.getFieldGoalsMade() + "-" + player.getFieldGoalsAttempted()));
        myViewHolder.playerThreePointers.setText(String.valueOf(player.getThreePointersMade()));
        myViewHolder.playerFreeThrows.setText(String.valueOf(player.getFreeThrowsMade()));
        myViewHolder.minutesPlayed.setText(String.valueOf(player.getMinutesPlayed()));

    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }

}

