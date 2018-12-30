package com.ralo.nbascoreboard.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ralo.nbascoreboard.Listeners.CustomItemClickListener;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.DataClasses.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {
    private final CustomItemClickListener listener;
    private ArrayList<Player> playerList;
    private ArrayList<Integer> playerLeaderStats;
    private static final int POINTS = 0;
    private static final int REBOUNDS = 1;
    private static final int ASSISTS = 2;

    public PlayerAdapter(ArrayList<Player> myPlayers, ArrayList<Integer> playerLeaderStats, CustomItemClickListener listener) {
        this.playerList = myPlayers;
        this.playerLeaderStats = playerLeaderStats;
        this.listener = listener;
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
        private ImageView playerImage;

        MyViewHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundColor(Color.parseColor("#282828"));
            playerName = itemView.findViewById(R.id.player_name);
            playerPoints = itemView.findViewById(R.id.player_points);
            playerRebounds = itemView.findViewById(R.id.player_rebounds);
            playerAssists = itemView.findViewById(R.id.player_assists);
            playerFieldGoals = itemView.findViewById(R.id.player_field_goals);
            playerThreePointers = itemView.findViewById(R.id.player_three_pointers);
            playerFreeThrows = itemView.findViewById(R.id.player_free_throws);
            minutesPlayed = itemView.findViewById(R.id.player_minutes);
            playerImage = itemView.findViewById(R.id.player_image);
        }
    }

    @NonNull
    @Override
    public PlayerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_player, parent, false);
        final PlayerAdapter.MyViewHolder myViewHolder = new PlayerAdapter.MyViewHolder(listItem);
        listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClick(v, myViewHolder.getAdapterPosition());
                return true;
            }
        });
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Player player = playerList.get(position);
        String formattedName = String.valueOf(player.getFirstName().charAt(0)) + ". " + player.getLastName();
        myViewHolder.playerName.setText(formattedName);
        myViewHolder.playerPoints.setText(String.valueOf(player.getPoints()));
        myViewHolder.playerRebounds.setText(String.valueOf(player.getReboundsTotal()));
        myViewHolder.playerAssists.setText(String.valueOf(player.getAssists()));
        myViewHolder.playerFieldGoals.setText(String.valueOf(player.getFieldGoalsMade() + "-" + player.getFieldGoalsAttempted()));
        myViewHolder.playerThreePointers.setText(String.valueOf(player.getThreePointersMade() + "-" + player.getThreePointersAttempted()));
        myViewHolder.playerFreeThrows.setText(String.valueOf(player.getFreeThrowsMade() + "-" + player.getFreeThrowsAttempted()));
        myViewHolder.minutesPlayed.setText(String.valueOf(player.getMinutesPlayed()));
        Picasso.get().load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" + player.getPlayerId() + ".png").into(myViewHolder.playerImage);
        if (player.getPoints() == playerLeaderStats.get(POINTS))
            myViewHolder.playerPoints.setTextColor(Color.parseColor("#ffffff"));
        if (player.getReboundsTotal() == playerLeaderStats.get(REBOUNDS))
            myViewHolder.playerRebounds.setTextColor(Color.parseColor("#ffffff"));
        if (player.getAssists() == playerLeaderStats.get(ASSISTS))
            myViewHolder.playerAssists.setTextColor(Color.parseColor("#ffffff"));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}

